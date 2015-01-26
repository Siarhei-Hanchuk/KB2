package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.WarriorEntity;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.ui.ImageCache;

public class BattleView extends View {
    private BattleController battleController;
    private int selectedX = -1;
    private int selectedY = -1;

    public BattleView(Context context, GameController gameController,
                      BattleController battleController, ViewClosable closeCallback) {
        super(context, gameController, closeCallback);
        this.battleController = battleController;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        calcOffsets();
        int x = (int)((event.getX() - xOffset) / stepX());
        int y = (int)((event.getY() - yOffset) / stepY());
        battleController.select(x, y);
        selectedX = x;
        selectedY = y;
        drawThread.refresh();

        return super.onTouchEvent(event);
    }

    public void draw(@NonNull Canvas canvas) {
        ImageCache imageCache = ImageCache.getInstance(getResources(), stepX(), stepY());
        calcOffsets();

        MapPoint[][] map = battleController.getMap();
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                canvas.drawBitmap(imageCache.getImage(map[x][y].getLand()),
                        xOffset + x * stepX(), yOffset + y * stepY(), null);
                WarriorEntity warrior = (WarriorEntity)map[x][y].getEntity();
                if (map[x][y].getEntity() != null) {
                    Bitmap image = imageCache.getImage(map[x][y].getEntity().getID());
                    if (!warrior.isFriendly()) {
                        image = flipImage(image);
                    }
                    canvas.drawBitmap(image, xOffset + x * stepX(), yOffset + y * stepY(), null);
                }
            }
        }

        if (selectedX >= 0 && selectedY >= 0) {
            canvas.drawBitmap(imageCache.getImage(R.drawable.battle),
                    xOffset + selectedX * stepX(),
                    yOffset + selectedY * stepY(),
                    null);
        }
    }

    private Bitmap flipImage(Bitmap src) {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
        dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        return dst;
    }
}
