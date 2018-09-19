package by.siarhei.kb2.app.platforms.android.touchers;

import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.platforms.android.XMainView;
import by.siarhei.kb2.app.platforms.android.helpers.Click;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;

public class ArmyShopToucher {
    public static void touchEvent(@NonNull MotionEvent event, XMainView mainView) {
        Click click = mainView.getClick(event);
        int buttonSize = mainView.getHeight() / 5;

        if (click.in(buttonSize * 2, buttonSize, buttonSize * 2, buttonSize,
                Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM)) {
            buyRequest(1);
        } else if (click.in(buttonSize, 0, buttonSize * 2, buttonSize,
                Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM)) {
            buyRequest(10);
        } else if (click.in(buttonSize * 2, buttonSize, buttonSize, 0,
                Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM)) {
            buyRequest(100);
        } else  if (click.in(buttonSize, 0, buttonSize, 0,
                Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM)) {
            buyRequest(1000);
        } else {
            exitRequest();
        }
    }

    private static void buyRequest(int count) {
        Request request = new Request();
        request.setAction(Request.ACTION_BUY_ARMY);
        request.setMenuItem(count);
        Server.getServer().request(request);
    }

    private static void exitRequest() {
        Request request = new Request();
        request.setAction(Request.ACTION_OK);
        Server.getServer().request(request);
    }
}
