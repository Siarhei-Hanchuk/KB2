package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.helpers.Click;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.GameGrid;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.Response;

public class GameView extends RootView {
    public GameView(MainView mainView) {
        super(mainView);
    }

    @Override
    public void draw(@NonNull Canvas canvas, Response response) {
        Painter painter = mainView.getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        GameGrid grid = response.getGameGrid();

        for (int x = 0; x < GameGrid.STEP_X; x++) {
            for (int y = 0; y < GameGrid.STEP_Y; y++) {
                if (x < 5) {
                    painter.drawBitmap(mainView.getImageCache().getImage(grid.getBackgroundBuyXY(x, y)),
                            mainView.stepX() * x, mainView.stepY() * y);
                }
                if (grid.getImageBuyXY(x, y) > -1) {
                    painter.drawBitmap(mainView.getImageCache().getImage(grid.getImageBuyXY(x, y)),
                            mainView.stepX() * x, mainView.stepY() * y);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        Click click = mainView.getClick(event);
        int x = click.getX();
        int y = click.getY();
        if (x > mainView.stepX() * 5) {
            int item = (y / mainView.stepY());
            touchMenu(item);
        } else {
            int height_2_5 = mainView.stepY() * 2;
            int height_3_5 = mainView.stepY() * 3;
            int width_2_5 = mainView.stepX() * 2;
            int width_3_5 = mainView.stepX() * 3;
            if (y > height_3_5) {
                if (x > width_2_5 && x < width_3_5) {
                    touchDown();
                }
                if (x < width_2_5) {
                    touchDownLeft();
                }
                if (x > width_3_5) {
                    touchDownRight();
                }
            }
            if (y < height_2_5) {
                if (x > width_2_5 && x < width_3_5) {
                    touchUp();
                }
                if (x < width_2_5) {
                    touchUpLeft();
                }
                if (x > width_3_5) {
                    touchUpRight();
                }
            }
            if (x > width_3_5 && y > height_2_5 && y < height_3_5) {
                touchRight();
            }
            if (x < width_2_5 && y > height_2_5 && y < height_3_5) {
                touchLeft();
            }
        }

        return true;
    }


    private void touchDown() {
        playerMove(0, +1);
    }

    private void touchUp() {
        playerMove(0, -1);
    }

    private void touchRight() {
        playerMove(+1, 0);
    }

    private void touchLeft() {
        playerMove(-1, 0);
    }

    private void touchUpRight() {
        playerMove(+1, -1);
    }

    private void touchUpLeft() {
        playerMove(-1, -1);
    }

    private void touchDownRight() {
        playerMove(+1, +1);
    }

    private void touchDownLeft() {
        playerMove(-1, +1);
    }

    private void playerMove(int dx, int dy) {
        Request request = new Request();
        request.setAction(Request.ACTION_MOVE);
        request.setMoveTo(dx, dy);
        Server.getServer().request(request);
    }

    private void touchMenu(int item) {
        Request request = new Request();
        request.setAction(Request.ACTION_OPEN_GAME_MENU);
        request.setMenuItem(item);
        Server.getServer().request(request);
    }
}
