package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.ArmyShopViewController;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.platforms.android.views.helpers.Click;
import com.neschur.kb2.app.platforms.android.views.helpers.Painter;
import com.neschur.kb2.app.warriors.Warrior;

class ArmyShopView extends ViewImpl {
    private final ArmyShop shop;
    private final Player player;
    private final Warrior warrior;
    private final ArmyShopViewController armyShopViewController;
    private int buttonSize;

    public ArmyShopView(Context context, ArmyShopViewController armyShopViewController, ArmyShop shop) {
        super(context, armyShopViewController);

        this.shop = shop;
        this.player = armyShopViewController.getPlayer();
        this.warrior = shop.getWarrior();
        this.armyShopViewController = armyShopViewController;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        Click click = getClick(event);
        if (click.in(buttonSize * 2, buttonSize, buttonSize * 2, buttonSize,
                Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM)) {
            armyShopViewController.buyArmy(shop, 1);
        } else if (click.in(buttonSize, 0, buttonSize * 2, buttonSize,
                Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM)) {
            armyShopViewController.buyArmy(shop, 10);
        } else if (click.in(buttonSize * 2, buttonSize, buttonSize, 0,
                Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM)) {
            armyShopViewController.buyArmy(shop, 100);
        } else  if (click.in(buttonSize, 0, buttonSize, 0,
                Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM)) {
            armyShopViewController.buyArmy(shop, 1000);
        } else {
            viewController.viewClose();
        }
        return false;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        Paint paint = new Paint(getDefaultPaint());
        Paint smallFont = new Paint(paint);
        smallFont.setTextSize(textHeight() / 2);

        this.buttonSize = getHeight() / 5;
        int buttonBorderSize = 5;
        Bitmap image = getImageCache().getImage(warrior.getId());

        painter.drawBitmap(image, 0, 0);
        painter.drawText(i18n.translate("army_names_" + warrior.getTextId()),
                stepX() + 10, textHeight(), paint);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_thereIs)
                        + ": " + shop.getCount(),
                stepX() + 10, menuItemHeight(), smallFont);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_price)
                        + ": " + warrior.getPriceInShop(),
                stepX() + 10, (int) (menuItemHeight() * 1.5), smallFont);

        String playerMoney = i18n.translate(R.string.player_attrs_money) + ": " + player.getMoney();
        painter.drawText(playerMoney, paint.measureText(playerMoney), textHeight() * 2, paint, Painter.ALIGN_RIGHT);

        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_step) + ": " + warrior.getStep(),
                0, stepY() + textHeight(), paint);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_defense) + ": " + warrior.getDefence(),
                0, stepY() + textHeight() * 2, paint);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_damage) + ": " + warrior.getDamage(),
                0, stepY() + textHeight() * 3, paint);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_fly) +
                        ": " + (warrior.isFly() ? i18n.translate(R.string.yes) : i18n.translate(R.string.no)),
                0, stepY() + textHeight() * 4, paint);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_shoot) +
                        ": " + (warrior.isShoot() ? i18n.translate(R.string.yes) : i18n.translate(R.string.no)),
                0, stepY() + textHeight() * 5, paint);

        String how = i18n.translate(R.string.entity_armyShop_ui_afford) + ": " + player.armyAfford(warrior);
        painter.drawText(how,
                smallFont.measureText(how),
                getHeight() - buttonSize * 2 - 10 - menuItemHeight(),
                smallFont, Painter.ALIGN_RIGHT);
        painter.drawText(i18n.translate(R.string.entity_armyShop_ui_howMany),
                buttonSize * 2,
                buttonSize * 2 - smallFont.getTextSize(),
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
                buttonSize / 1.5f,
                buttonSize + buttonSize / 2,
                smallFont, Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM);
        painter.drawText("100",
                buttonSize + buttonSize / 1.2f,
                buttonSize / 2,
                smallFont, Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM);
        painter.drawText("1000",
                buttonSize / 1.12f,
                buttonSize / 2,
                smallFont, Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM);
    }
}
