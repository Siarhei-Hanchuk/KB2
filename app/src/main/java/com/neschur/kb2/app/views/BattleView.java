package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.WarriorEntity;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.MapPointBattle;
import com.neschur.kb2.app.ui.ImageCache;

public class BattleView extends View {
    private BattleController battleController;

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
        drawThread.refresh();

        return super.onTouchEvent(event);
    }

    public void draw(@NonNull Canvas canvas) {
        ImageCache imageCache = ImageCache.getInstance(getResources(), stepX(), stepY());
        calcOffsets();
        Paint countPaint = getDefaultPaint();
        countPaint.setTextSize(textHeight() / 3);
        Paint countPaintBg = getDefaultPaint();
        countPaintBg.setColor(Color.BLACK);

        MapPointBattle[][] map = battleController.getMap();
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                canvas.drawBitmap(imageCache.getImage(map[x][y].getLand()),
                        xOffset + x * stepX(), yOffset + y * stepY(), null);
                if (map[x][y].isMove())
                    canvas.drawBitmap(imageCache.getImage(R.drawable.battle_move),
                            xOffset + x * stepX(), yOffset + y * stepY(), null);
                WarriorEntity warrior = map[x][y].getEntity();
                if (map[x][y].getEntity() != null) {
                    Bitmap image = imageCache.getImage(map[x][y].getEntity().getID());
                    if (!warrior.isFriendly()) {
                        image = flipImage(image);
                    }
                    canvas.drawBitmap(image, xOffset + x * stepX(), yOffset + y * stepY(), null);
                    canvas.drawRect(xOffset + x * stepX(),
                            yOffset + y * stepY() + stepY() - textHeight() / 3,
                            xOffset + x * stepX() +
                                    countPaint.measureText(
                                            Integer.toString(warrior.getCount())) + 4,
                            yOffset + y * stepY() + stepY(),
                            countPaintBg);
                    canvas.drawText(Integer.toString(warrior.getCount()),
                            xOffset + x * stepX() + 2,
                            yOffset + y * stepY() + stepY() - 2,
                            countPaint);
                }
            }
        }

        if (battleController.getSelectedX() >= 0 && battleController.getSelectedY() >= 0) {
            canvas.drawBitmap(imageCache.getImage(R.drawable.battle_select),
                    xOffset + battleController.getSelectedX() * stepX(),
                    yOffset + battleController.getSelectedY() * stepY(),
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
