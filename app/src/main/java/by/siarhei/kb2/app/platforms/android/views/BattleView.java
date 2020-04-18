package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.helpers.Click;
import by.siarhei.kb2.app.server.GameGrid;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Response;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.models.battle.MapPoint;
import by.siarhei.kb2.app.server.models.battle.Entity;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;

public class BattleView extends RootView {
    private static final int IMAGE_WIDTH = 96;
    private static final int IMAGE_HEIGHT = 82;

    private Paint countPaint;
    private Paint countPaintBg;

    public BattleView(MainView mainView) {
        super(mainView);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        Click click = getClick(event);
        int x = click.getX() / stepX();
        int y = click.getY() / stepY();

        Request request = new Request();
        request.setAction(Request.ACTION_BATTLE_MOVE);
        request.setMoveTo(x, y);
        Server.getServer().request(request);

        return true;
    }

    @Override
    public void draw(@NonNull Canvas canvas, Response response) {
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        MapPoint[][] map = response.getBattleField().getMapPoints();
        drawLand(painter, map);

        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if(map[x][y] == response.getBattleField().getSelectedPoint()) {
                    painter.drawBitmap(getImageCache().getImage(R.drawable.battle_select), stepX() * x, stepY() * y);
                }
//
                if (map[x][y].isMovable())
                    drawMoveCircle(painter, x, y, map);
//                    painter.drawBitmap(getImageCache().getImage(R.drawable.battle_select), stepX() * x, stepY() * y);
                if (map[x][y].getEntity() != null) {
                    drawWarrior(painter, x, y, map);
                }
            }
        }
    }

    private void drawLand(Painter painter, MapPoint[][] mapPoints) {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                painter.drawBitmap(getImageCache().getImage(mapPoints[x][y].getLand()),
                        stepX() * x, stepY() * y);
            }
        }
    }

    private void drawMoveCircle(Painter painter, int x, int y, MapPoint[][] map) {
        MapPoint point = map[x][y];
        if (point.getEntity() != null) {
            if (!point.getEntity().isPlayerEntity())
                painter.drawBitmap(getImageCache().getImage(R.drawable.battle_attack),
                        stepX() * x, stepY() * y);
        } else {
            painter.drawBitmap(getImageCache().getImage(R.drawable.battle_move),
                    stepX() * x, stepY() * y);
        }
    }

    private void drawWarrior(Painter painter, int x, int y, MapPoint[][] map) {
        MapPoint point = map[x][y];
        Entity warrior = point.getEntity();
        Bitmap image = getImageCache().getImage(point.getEntity().getID());
        if (!warrior.isPlayerEntity()) {
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

    private Bitmap flipImage(Bitmap src) {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
        dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        return dst;
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

    private Click getClick(@NonNull MotionEvent event) {
        int[] offsets = calcOffsets();
        return new Click(event, offsets[0], offsets[1], getWidth(), getHeight());
    }

    private int[] calcOffsets() {
        double scaleX = (double) getWidth() / (IMAGE_WIDTH * GameGrid.STEP_X);
        double scaleY = (double) getHeight() / (IMAGE_HEIGHT * GameGrid.STEP_Y);
        int[] offsets = new int[2];
        if (scaleX > scaleY) {
            offsets[0] = (getWidth() - stepX() * GameGrid.STEP_X) / 2;
        } else {
            offsets[0] = (getHeight() - stepX() * GameGrid.STEP_Y) / 2;
        }

        return offsets;
    }
}
