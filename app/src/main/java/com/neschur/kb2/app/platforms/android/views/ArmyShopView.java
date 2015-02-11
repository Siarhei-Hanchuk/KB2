package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.ArmyShopViewController;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.models.Player;
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
        if (event.getX() < getWidth() - buttonSize * 2 ||
                event.getY() < getHeight() - buttonSize * 2) {
            viewController.viewClose();
        } else {
            if (event.getX() > getWidth() - buttonSize * 2 &&
                    event.getX() < getWidth() - buttonSize &&
                    event.getY() > getHeight() - buttonSize * 2 &&
                    event.getY() < getHeight() - buttonSize) {
                armyShopViewController.buyArmy(shop, 1);
            }
            if (event.getX() > getWidth() - buttonSize &&
                    event.getY() > getHeight() - buttonSize * 2 &&
                    event.getY() < getHeight() - buttonSize) {
                armyShopViewController.buyArmy(shop, 10);
            }
            if (event.getX() > getWidth() - buttonSize * 2 &&
                    event.getX() < getWidth() - buttonSize &&
                    event.getY() > getHeight() - buttonSize) {
                armyShopViewController.buyArmy(shop, 100);
            }
            if (event.getX() > getWidth() - buttonSize &&
                    event.getY() > getHeight() - buttonSize) {
                armyShopViewController.buyArmy(shop, 1000);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);

        Paint paint = getDefaultPaint();
        Paint smallFont = new Paint(paint);
        smallFont.setTextSize(textHeight() / 2);

        this.buttonSize = getHeight() / 5;
        int imageHeight = stepY();
        int buttonBorderSize = 5;
        Bitmap image = getImageCache().getImage(warrior.getId());

        canvas.drawBitmap(image, stepX(0), stepY(0), null);
        canvas.drawText(i18n.translate("army_names_" + warrior.getTextId()),
                stepX(1) + 10, textHeight(), paint);
        canvas.drawText(i18n.translate(R.string.entity_armyShop_ui_thereIs)
                        + ": " + shop.getCount(),
                stepX(1) + 10, menuItemHeight(), smallFont);
        canvas.drawText(i18n.translate(R.string.entity_armyShop_ui_price)
                        + ": " + warrior.getPriceInShop(),
                stepX(1) + 10, (int) (menuItemHeight() * 1.5), smallFont);

        String playerMoney = i18n.translate(R.string.player_attrs_money) + ": " + player.getMoney();
        canvas.drawText(playerMoney,
                getWidth() - xOffset - paint.measureText(playerMoney), textHeight() * 2, paint);

        canvas.drawText(i18n.translate(R.string.entity_armyShop_ui_step) + ": " + warrior.getStep(),
                stepX(0), stepY(1) + textHeight(), paint);
        canvas.drawText(i18n.translate(R.string.entity_armyShop_ui_defense) + ": " + warrior.getDefence(),
                stepX(0), stepY(1) + textHeight() * 2, paint);
        canvas.drawText(i18n.translate(R.string.entity_armyShop_ui_damage) + ": " + warrior.getDamage(),
                stepX(0), stepY(1) + textHeight() * 3, paint);
        canvas.drawText(i18n.translate(R.string.entity_armyShop_ui_fly) +
                        ": " + (warrior.isFly() ? i18n.translate(R.string.yes) : i18n.translate(R.string.no)) ,
                stepX(0), stepY(1) + textHeight() * 4, paint);
        canvas.drawText(i18n.translate(R.string.entity_armyShop_ui_shoot) +
                        ": " + (warrior.isShoot() ? i18n.translate(R.string.yes) : i18n.translate(R.string.no)) ,
                stepX(0), stepY(1) + textHeight() * 5, paint);

        String how = i18n.translate(R.string.entity_armyShop_ui_afford) + ": " + player.armyAfford(warrior);
        canvas.drawText(how,
                getWidth() - smallFont.measureText(how) - xOffset,
                getHeight() - buttonSize * 2 - 10 - menuItemHeight(), smallFont);
        canvas.drawText(i18n.translate(R.string.entity_armyShop_ui_howMany),
                getWidth() - buttonSize * 2,
                getHeight() - buttonSize * 2 - 10, smallFont);
        paint.setColor(Color.GRAY);
        canvas.drawRect(getWidth() - buttonSize * 2, getHeight() - buttonSize * 2,
                getWidth(), getHeight(), paint);
        paint.setColor(Color.BLACK);
        canvas.drawRect(getWidth() - buttonSize + buttonBorderSize,
                getHeight() - buttonSize + buttonBorderSize,
                getWidth() - buttonBorderSize,
                getHeight() - buttonBorderSize, paint);
        canvas.drawRect(getWidth() - buttonSize * 2 + buttonBorderSize,
                getHeight() - buttonSize * 2 + buttonBorderSize,
                getWidth() - buttonSize - buttonBorderSize,
                getHeight() - buttonSize - buttonBorderSize, paint);
        canvas.drawRect(getWidth() - buttonSize + buttonBorderSize,
                getHeight() - buttonSize * 2 + buttonBorderSize,
                getWidth() - buttonBorderSize,
                getHeight() - buttonSize - buttonBorderSize, paint);
        canvas.drawRect(getWidth() - buttonSize * 2 + buttonBorderSize,
                getHeight() - buttonSize + buttonBorderSize,
                getWidth() - buttonSize - buttonBorderSize,
                getHeight() - buttonBorderSize, paint);

        paint.setColor(Color.WHITE);
        canvas.drawText("1",
                getWidth() - buttonSize - buttonSize / 2,
                getHeight() - buttonSize - buttonSize / 2, smallFont);
        canvas.drawText("10",
                getWidth() - buttonSize / 1.5f,
                getHeight() - buttonSize - buttonSize / 2, smallFont);
        canvas.drawText("100",
                getWidth() - buttonSize - buttonSize / 1.2f,
                getHeight() - buttonSize / 2, smallFont);
        canvas.drawText("1000",
                getWidth() - buttonSize / 1.12f,
                getHeight() - buttonSize / 2, smallFont);
    }
}
