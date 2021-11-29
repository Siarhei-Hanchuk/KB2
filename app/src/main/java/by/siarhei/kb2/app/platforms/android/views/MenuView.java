package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Canvas;
import android.graphics.Color;
import androidx.annotation.NonNull;

import android.view.KeyEvent;
import android.view.MotionEvent;

import java.security.Key;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.Response;
import by.siarhei.kb2.app.ui.menus.Menu;

public class MenuView extends RootView {
    public MenuView(MainView mainView) {
        super(mainView);
    }

    public void draw(@NonNull Canvas canvas, Response response) {
        Painter painter = mainView.getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        Menu menu = response.getMenu();
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
                    + response.getMoney();
            painter.drawText(money,
                    mainView.getDefaultPaint().measureText(money) + 1,
                    mainView.textHeight() + mainView.menuItemHeight() * i, mainView.getDefaultPaint(), Painter.ALIGN_RIGHT);
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event){
        double y = event.getY();
        int item = (int) y / menuItemHeight();
        menuItemSelect(item);

        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        int item = keyCode - KeyEvent.KEYCODE_0 - 1;
        Menu menu = Server.getServer().getResponse().getMenu();
        if ((keyCode == KeyEvent.KEYCODE_ESCAPE || keyCode == KeyEvent.KEYCODE_GRAVE) && menu.withExit()) {
            item = menu.getCount();
        }
        menuItemSelect(item);
        return true;
    }

    private void menuItemSelect(int item) {
        Menu menu = Server.getServer().getResponse().getMenu();
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
