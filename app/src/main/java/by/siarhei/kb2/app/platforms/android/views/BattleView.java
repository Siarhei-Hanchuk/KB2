package by.siarhei.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.controllers.BattleController;
import by.siarhei.kb2.app.models.battle.MapPointBattle;
import by.siarhei.kb2.app.models.battle.WarriorEntity;
import by.siarhei.kb2.app.platforms.android.views.helpers.Click;
import by.siarhei.kb2.app.platforms.android.views.helpers.Painter;

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
        Click click = getClick(event);
        int x = click.getX() / stepX();
        int y = click.getY() / stepY();
        battleController.select(x, y);

        return false;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);

        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        drawLand(painter);

        MapPointBattle[][] map = battleController.getMap();
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (map[x][y].isMove())
                    drawMoveCircle(painter, x, y);
                if (map[x][y].getEntity() != null) {
                    drawWarrior(painter, x, y);
                }
            }
        }

        drawSelected(painter);
    }

    private void drawLand(Painter painter) {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                painter.drawBitmap(getImageCache().getImage(getMap()[x][y].getLand()),
                        stepX() * x, stepY() * y);
            }
        }
    }

    private void drawMoveCircle(Painter painter, int x, int y) {
        MapPointBattle point = getMap()[x][y];
        if (point.getEntity() != null) {
            if (!point.getEntity().isFriendly())
                painter.drawBitmap(getImageCache().getImage(R.drawable.battle_attack),
                        stepX() * x, stepY() * y);
        } else {
            painter.drawBitmap(getImageCache().getImage(R.drawable.battle_move),
                    stepX() * x, stepY() * y);
        }
    }

    private void drawWarrior(Painter painter, int x, int y) {
        MapPointBattle point = getMap()[x][y];
        WarriorEntity warrior = point.getEntity();
        Bitmap image = getImageCache().getImage(point.getEntity().getID());
        if (!warrior.isFriendly()) {
            image = flipImage(image);
        }
        painter.drawBitmap(image, stepX() * x, stepY() * y);
        painter.drawRect(stepX() * x,
                stepY() * y + stepY() - textHeight() / 3,
                stepX() * x +
                        getCountPaint().measureText(
                                Integer.toString(warrior.getCount())) + 4,
                stepY() * y + stepY(),
                getCountPaintBg());
        painter.drawText(Integer.toString(warrior.getCount()),
                stepX() * x + 2,
                stepY() * y + stepY() - 2,
                getCountPaint());
    }

    private void drawSelected(@NonNull Painter painter) {
        if (battleController.getSelectedX() >= 0 && battleController.getSelectedY() >= 0) {
            painter.drawBitmap(getImageCache().getImage(R.drawable.battle_select),
                    stepX() * battleController.getSelectedX(),
                    stepY() * battleController.getSelectedY());
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
            countPaint = new Paint();
            countPaint.setTextSize(textHeight() / 3);
            countPaint.setColor(Color.WHITE);
        }
        return countPaint;
    }

    private Paint getCountPaintBg() {
        if (countPaintBg == null) {
            countPaintBg = new Paint();
            countPaintBg.setColor(Color.BLACK);
        }
        return countPaintBg;
    }
}
