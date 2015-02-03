package com.neschur.kb2.app.platforms.android.views;

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
import com.neschur.kb2.app.models.battle.MapPointBattle;
import com.neschur.kb2.app.models.battle.WarriorEntity;

public class BattleView extends ViewImpl {
    private final BattleController battleController;
    private Paint countPaint;
    private Paint countPaintBg;

    public BattleView(Context context, BattleController battleController) {
        super(context, battleController);
        this.battleController = battleController;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        calcOffsets();
        int x = (int) ((event.getX() - xOffset) / stepX());
        int y = (int) ((event.getY() - yOffset) / stepY());
        battleController.select(x, y);

        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        calcOffsets();

        drawLand(canvas);

        MapPointBattle[][] map = battleController.getMap();
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (map[x][y].isMove())
                    drawMoveCircle(canvas, x, y);
                if (map[x][y].getEntity() != null) {
                    drawWarrior(canvas, x, y);
                }
            }
        }

        drawSelected(canvas);
    }

    @Override
    public void refresh() {
        drawThread.refresh();
    }

    private void drawLand(Canvas canvas) {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                canvas.drawBitmap(getImageCache().getImage(getMap()[x][y].getLand()),
                        stepX(x), stepY(y), null);
            }
        }
    }

    private void drawMoveCircle(Canvas canvas, int x, int y) {
        MapPointBattle point = getMap()[x][y];
        if (point.getEntity() != null) {
            if (!point.getEntity().isFriendly())
                canvas.drawBitmap(getImageCache().getImage(R.drawable.battle_attack),
                        stepX(x), stepY(y), null);
        } else {
            canvas.drawBitmap(getImageCache().getImage(R.drawable.battle_move),
                    stepX(x), stepY(y), null);
        }
    }

    private void drawWarrior(Canvas canvas, int x, int y) {
        MapPointBattle point = getMap()[x][y];
        WarriorEntity warrior = point.getEntity();
        Bitmap image = getImageCache().getImage(point.getEntity().getID());
        if (!warrior.isFriendly()) {
            image = flipImage(image);
        }
        canvas.drawBitmap(image, stepX(x), stepY(y), null);
        canvas.drawRect(stepX(x),
                stepY(y) + stepY() - textHeight() / 3,
                stepX(x) +
                        getCountPaint().measureText(
                                Integer.toString(warrior.getCount())) + 4,
                stepY(y) + stepY(),
                getCountPaintBg());
        canvas.drawText(Integer.toString(warrior.getCount()),
                stepX(x) + 2,
                stepY(y) + stepY() - 2,
                getCountPaint());
    }

    private void drawSelected(@NonNull Canvas canvas) {
        if (battleController.getSelectedX() >= 0 && battleController.getSelectedY() >= 0) {
            canvas.drawBitmap(getImageCache().getImage(R.drawable.battle_select),
                    stepX(battleController.getSelectedX()),
                    stepY(battleController.getSelectedY()),
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

    private MapPointBattle[][] getMap() {
        return battleController.getMap();
    }

    private Paint getCountPaint() {
        if (countPaint == null) {
            countPaint = getDefaultPaint();
            countPaint.setTextSize(textHeight() / 3);
        }
        return countPaint;
    }

    private Paint getCountPaintBg() {
        if (countPaintBg == null) {
            countPaintBg = getDefaultPaint();
            countPaintBg.setColor(Color.BLACK);
        }
        return countPaintBg;
    }
}
