package by.siarhei.kb2.app.platforms.android.touchers;

import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.platforms.android.XMainView;
import by.siarhei.kb2.app.platforms.android.helpers.Click;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;

public class GameToucher {
    public static void touchEvent(@NonNull MotionEvent event, XMainView mainView) {
        Click click = mainView.getClick(event);
        int x = click.getX();
        int y = click.getY();
        if (x > mainView.stepX() * 5) {
            int item = (y / mainView.stepY());
//            touchMenu(item);
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
    }


    public static void touchDown() {
        playerMove(0, +1);
    }

    public static void touchUp() {
        playerMove(0, -1);
    }

    public static void touchRight() {
        playerMove(+1, 0);
    }

    public static void touchLeft() {
        playerMove(-1, 0);
    }

    public static void touchUpRight() {
        playerMove(+1, -1);
    }

    public static void touchUpLeft() {
        playerMove(-1, -1);
    }

    public static void touchDownRight() {
        playerMove(+1, +1);
    }

    public static void touchDownLeft() {
        playerMove(-1, +1);
    }

    private static void playerMove(int dx, int dy) {
        Request request = new Request();
        request.setAction(Request.ACTION_MOVE);
        request.setMoveTo(dx, dy);
        Server.getServer().request(request);
    }

//    private static touchMenu(item)

    public static Click getClick(@NonNull MotionEvent event, XMainView mainView) {
        int[] offsets = mainView.calcOffsets();
        return new Click(event, offsets[0], offsets[1], mainView.getWidth(), mainView.getHeight());
    }
}
