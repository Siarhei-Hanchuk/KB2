package by.siarhei.kb2.app.platforms.android.drawers;

import android.app.usage.UsageEvents;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.platforms.android.views.RootView;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;
import by.siarhei.kb2.app.ui.menus.Menu;

public class MenuView extends RootView {
    public MenuView(MainView mainView) {
        super(mainView);
    }

    public void draw(@NonNull Canvas canvas, ServerView serverView) {
        Painter painter = mainView.getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        Menu menu = serverView.getMenu();
        int i;
        for (i = 0; i < menu.getCount(); i++) {
            painter.drawText(menu.getItemDescription(i), 10,
                    mainView.textHeight() + mainView.menuItemHeight() * i, mainView.getDefaultPaint());
        }
        if (menu.withExit())
            painter.drawText(Server.getI18n().translate(R.string.mainMenu_exit), 10,
                    mainView.textHeight() + mainView.menuItemHeight() * i, mainView.getDefaultPaint());
        if (menu.withMoney()) {
            String money = i18n.translate(R.string.player_attrs_money) + ": "
                    + serverView.getMoney();
            painter.drawText(money,
                    mainView.getDefaultPaint().measureText(money) + 1,
                    mainView.textHeight() + mainView.menuItemHeight() * i, mainView.getDefaultPaint(), Painter.ALIGN_RIGHT);
        }
    }

    @Override
    public void onTouchEvent(@NonNull MotionEvent event){
        Menu menu = Server.getServer().getView().getMenu();
        double y = event.getY();
        int item = (int) y / menuItemHeight();

        int count = menu.getCount();
        if(menu.withExit()) {
            count++;
        }
        if (item < count) {
            Request request = new Request();
            request.setAction(Request.ACTION_SELECT);
            request.setMenuItem(item);
            Server.getServer().request(request);
        }
    }
}
