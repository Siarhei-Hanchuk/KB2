package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.helpers.Click;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;
import by.siarhei.kb2.app.server.warriors.Warrior;

public class ArmyShopView extends RootView {
    public ArmyShopView(MainView mainView) {
        super(mainView);
    }

    public void draw(@NonNull Canvas canvas, ServerView serverView) {
        Painter painter = mainView.getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        Paint paint = new Paint(mainView.getDefaultPaint());
        Paint smallFont = new Paint(paint);
        smallFont.setTextSize(mainView.textHeight() / 2);

        int buttonSize = mainView.getHeight() / 5;
        I18n i18n = Server.getI18n();
        int buttonBorderSize = 5;
        Warrior warrior = serverView.getArmyShop().getWarrior();

        Bitmap image = mainView.getImageCache().getImage(warrior.getId());

        painter.drawBitmap(image, 0, 0);
        painter.drawText(i18n.translate("army_names_" + warrior.getTextId()),
                mainView.stepX() + 10, mainView.textHeight(), paint);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_thereIs)
                        + ": " + serverView.getArmyShop().getCount(),
                mainView.stepX() + 10, mainView.menuItemHeight(), smallFont);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_price)
                        + ": " + warrior.getPriceInShop(),
                mainView.stepX() + 10, (int) (mainView.menuItemHeight() * 1.5), smallFont);

        String playerMoney = i18n.translate(R.string.player_attrs_money) + ": " + serverView.getMoney();
        painter.drawText(playerMoney, 0, mainView.textHeight() * 2, paint, Painter.ALIGN_RIGHT);

        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_step) + ": " + warrior.getStep(),
                0, mainView.stepY() + mainView.textHeight(), paint);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_defense) + ": " + warrior.getDefence(),
                0, mainView.stepY() + mainView.textHeight() * 2, paint);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_damage) + ": " + warrior.getDamage(),
                0, mainView.stepY() + mainView.textHeight() * 3, paint);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_fly) +
                        ": " + (warrior.isFly() ? i18n.translate(R.string.yes) : i18n.translate(R.string.no)),
                0, mainView.stepY() + mainView.textHeight() * 4, paint);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_shoot) +
                        ": " + (warrior.isShoot() ? i18n.translate(R.string.yes) : i18n.translate(R.string.no)),
                0, mainView.stepY() + mainView.textHeight() * 5, paint);

        String how = i18n.translate(R.string.entity_armyShop_ui_afford) + ": " + serverView.getPlayer().armyAfford(warrior);
        painter.drawText(how,
                0,
                mainView.getHeight() - buttonSize * 2 - 10 - mainView.menuItemHeight(),
                smallFont, Painter.ALIGN_RIGHT);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_howMany),
                0,
                buttonSize * 2 + smallFont.getTextSize(),
                smallFont, Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM);
        paint.setColor(Color.GRAY);
        painter.drawRect(buttonSize * 2, buttonSize * 2, 0, 0,
                paint, Painter.ALIGN_BOTTOM + Painter.ALIGN_RIGHT);
        paint.setColor(Color.BLACK);
        painter.drawRect(buttonSize - buttonBorderSize,
                buttonSize - buttonBorderSize,
                buttonBorderSize,
                buttonBorderSize, paint, Painter.ALIGN_BOTTOM + Painter.ALIGN_RIGHT);
        painter.drawRect(buttonSize * 2 - buttonBorderSize,
                buttonSize * 2 - buttonBorderSize,
                buttonSize + buttonBorderSize,
                buttonSize + buttonBorderSize,
                paint, Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM);
        painter.drawRect(buttonSize - buttonBorderSize,
                buttonSize * 2 - buttonBorderSize,
                buttonBorderSize,
                buttonSize + buttonBorderSize,
                paint, Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM);
        painter.drawRect(buttonSize * 2 - buttonBorderSize,
                buttonSize - buttonBorderSize,
                buttonSize + buttonBorderSize,
                buttonBorderSize,
                paint, Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM);

        paint.setColor(Color.WHITE);
        painter.drawText("1",
                buttonSize + buttonSize / 2,
                buttonSize + buttonSize / 2,
                smallFont, Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM);
        painter.drawText("10",
                buttonSize / 2,
                buttonSize + buttonSize / 2,
                smallFont, Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM);
        painter.drawText("100",
                buttonSize + buttonSize / 2,
                buttonSize / 2,
                smallFont, Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM);
        painter.drawText("1000",
                buttonSize / 2,
                buttonSize / 2,
                smallFont, Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
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

        return true;
    }

    private void buyRequest(int count) {
        Request request = new Request();
        request.setAction(Request.ACTION_BUY_ARMY);
        request.setMenuItem(count);
        Server.getServer().request(request);
    }

    private void exitRequest() {
        Request request = new Request();
        request.setAction(Request.ACTION_OK);
        Server.getServer().request(request);
    }
}
