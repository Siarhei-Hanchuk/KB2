package by.siarhei.kb2.app.platforms.android.drawers;

import android.graphics.Canvas;
import android.graphics.Color;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.platforms.android.XMainView;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;
import by.siarhei.kb2.app.ui.menus.Menu;

import static by.siarhei.kb2.app.controllers.ApplicationController.i18n;

public class MenuDrawer extends Drawer {
    public MenuDrawer(Canvas canvas, XMainView mainView) {
        super(canvas, mainView);
    }

    public void draw(ServerView serverView) {
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
}
