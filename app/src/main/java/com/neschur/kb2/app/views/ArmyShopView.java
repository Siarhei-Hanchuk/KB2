package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.warriors.Warrior;

public class ArmyShopView extends View {
    private ArmyShop shop;
    private Warrior warrior;
    private int buttonSize;

    public ArmyShopView(Context context, ArmyShop shop, GameController gameController, ViewClosable closeCallback) {
        super(context, gameController, closeCallback);

        this.shop = shop;
        this.warrior = shop.getWarrior();
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (event.getX() < getWidth() - buttonSize * 2 ||
                event.getY() < getHeight() - buttonSize * 2) {
            closeCallback.viewClose();
        } else {
            if (event.getX() > getWidth() - buttonSize * 2 &&
                    event.getX() < getWidth() - buttonSize &&
                    event.getY() > getHeight() - buttonSize * 2 &&
                    event.getY() < getHeight() - buttonSize) {
                gameController.buyArmy(shop, 1);
            }
            if (event.getX() > getWidth() - buttonSize &&
                    event.getY() > getHeight() - buttonSize * 2 &&
                    event.getY() < getHeight() - buttonSize) {
                gameController.buyArmy(shop, 10);
            }
            if (event.getX() > getWidth() - buttonSize * 2 &&
                    event.getX() < getWidth() - buttonSize &&
                    event.getY() > getHeight() - buttonSize) {
                gameController.buyArmy(shop, 100);
            }
            if (event.getX() > getWidth() - buttonSize &&
                    event.getY() > getHeight() - buttonSize) {
                gameController.buyArmy(shop, 1000);
            }
        }
        drawThread.refresh();
        return super.onTouchEvent(event);
    }

    public void draw(@NonNull Canvas canvas) {
        Paint paint = getDefaultPaint();
        canvas.drawColor(Color.BLACK);

        this.buttonSize = getHeight() / 5;
        int imageWidth = (getWidth() / 6) * 3 / 2;
        int imageHeight = (getHeight() / 5) * 3 / 2;
        int buttonBorderSize = 5;
        Bitmap image = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        getContext().getResources(), warrior.getId()
                ),
                imageWidth, imageHeight, false
        );

        canvas.drawBitmap(image, 0, 0, null);
        canvas.drawText(I18n.translate(R.string.army_ui_step) + ": " + warrior.getStep(),
                0, imageHeight + menuItemHeight(), paint);
        canvas.drawText(I18n.translate(R.string.army_ui_defense) + ": " + warrior.getDefence(),
                0, imageHeight + menuItemHeight() * 2, paint);
        canvas.drawText(I18n.translate(R.string.army_ui_damage) + ": " + warrior.getDamage(),
                0, imageHeight + menuItemHeight() * 3, paint);
        canvas.drawText(I18n.translate(R.string.army_ui_fly) + ": " + warrior.isFly(),
                0, imageHeight + menuItemHeight() * 4, paint);
        canvas.drawText(I18n.translate(R.string.army_ui_shoot) + ": " + warrior.isShoot(),
                0, imageHeight + menuItemHeight() * 5, paint);

        canvas.drawText(I18n.translate("army_names_" + warrior.getTextId()),
                imageWidth + 10, menuItemHeight(), paint);
        canvas.drawText(I18n.translate(R.string.army_ui_thereIs) + ": " + shop.getCount(),
                imageWidth + 10, menuItemHeight() * 3, paint);
        canvas.drawText(I18n.translate(R.string.army_ui_price) + ": " + warrior.getPriceInShop(),
                imageWidth + 10, menuItemHeight() * 4, paint);
        canvas.drawText(I18n.translate(R.string.player_attrs_money) + ": " + player.getMoney(),
                getWidth() - imageWidth * 2, menuItemHeight() * 2, paint);

        canvas.drawText(I18n.translate(R.string.army_ui_afford) + ": " + player.armyAfford(warrior),
                getWidth() - buttonSize * 5,
                getHeight() - buttonSize * 2 - 10 - menuItemHeight(), paint);
        canvas.drawText(I18n.translate(R.string.army_ui_howMany),
                getWidth() - buttonSize * 2,
                getHeight() - buttonSize * 2 - 10, paint);
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
                getHeight() - buttonSize - buttonSize / 2, paint);
        canvas.drawText("10",
                getWidth() - buttonSize / 1.5f,
                getHeight() - buttonSize - buttonSize / 2, paint);
        canvas.drawText("100",
                getWidth() - buttonSize - buttonSize / 1.2f,
                getHeight() - buttonSize / 2, paint);
        canvas.drawText("1000",
                getWidth() - buttonSize / 1.12f,
                getHeight() - buttonSize / 2, paint);
    }
}
