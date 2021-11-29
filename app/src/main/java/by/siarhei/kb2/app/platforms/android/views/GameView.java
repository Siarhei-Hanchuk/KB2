package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Canvas;
import android.graphics.Color;
import androidx.annotation.NonNull;

import android.view.KeyEvent;
import android.view.MotionEvent;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.Sound;
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
            int vertical = 0;
            int horizontal = 0;
            if (y > height_3_5) {
                vertical = +1;
            }
            if (y < height_2_5) {
                vertical = -1;
            }
            if (x < width_2_5) {
                horizontal = -1;
            }
            if (x > width_3_5) {
                horizontal = +1;
            }
            playerStep(horizontal, vertical);
        }

        return true;
    }


    @Override
    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN || keyCode == KeyEvent.KEYCODE_2) {
            playerStep(0, +1);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_8) {
            playerStep(0, -1);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT || keyCode == KeyEvent.KEYCODE_6) {
            playerStep(+1, 0);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_4) {
            playerStep(-1, 0);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN_LEFT || keyCode == KeyEvent.KEYCODE_1) {
            playerStep(-1, +1);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN_RIGHT || keyCode == KeyEvent.KEYCODE_3) {
            playerStep(+1, +1);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP_LEFT || keyCode == KeyEvent.KEYCODE_7) {
            playerStep(-1, -1);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP_RIGHT || keyCode == KeyEvent.KEYCODE_9) {
            playerStep(+1, -1);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_Q) {
            touchMenu(0);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_W) {
            touchMenu(1);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_E) {
            touchMenu(2);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_R) {
            touchMenu(3);
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_T) {
            touchMenu(4);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private void playerStep(int dx, int dy) {
        playerMove(dx, dy);
        Sound.play(R.raw.step);
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
