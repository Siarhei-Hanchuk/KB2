package by.siarhei.kb2.app.platforms.android.touchers;

import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.platforms.android.XMainView;
import by.siarhei.kb2.app.platforms.android.helpers.Click;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;

public class Menu2Toucher {
    public static void touchEvent(@NonNull MotionEvent event, XMainView mainView) {
        Painter painter = mainView.getPainter(null);
        double y = event.getY();
        double x = event.getX();
        int item = -1;
        if(x > painter.getXOffset() && y > mainView.stepY() * 2 && y < mainView.stepY() * 3) {
            item = ((int) x - painter.getXOffset()) / mainView.stepX();
        }
        selectRequest(item);
    }

    private static void selectRequest(int item) {
        Request request = new Request();
        request.setAction(Request.ACTION_SELECT);
        request.setMenuItem(item);
        Server.getServer().request(request);
    }
}
