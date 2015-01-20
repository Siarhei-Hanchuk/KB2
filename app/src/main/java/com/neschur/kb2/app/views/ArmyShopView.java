package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.warriors.Warrior;

public class ArmyShopView extends View {
    private ArmyShop shop;
    private Warrior warrior;

    public ArmyShopView(Context context, ArmyShop shop, GameController gameController, ViewClosable closeCallback) {
        super(context, gameController, closeCallback);

        this.shop = shop;
        this.warrior = shop.getWarrior();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        closeCallback.viewClose();
        drawThread.refresh();
        return super.onTouchEvent(event);
    }

    public void draw(Canvas canvas) {
        int imageWidth = (getWidth() / 6) * 3/2;
        int imageHeight = (getHeight() / 5) * 3/2;
        Bitmap image = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        getContext().getResources(), warrior.getId()
                ),
                imageWidth, imageHeight, false
        );

        canvas.drawBitmap(image, 0, 0, null);
        canvas.drawText(I18n.translate(R.string.army_ui_step) + ": " + warrior.getStep(),
                0, imageHeight + ITEM_SIZE * 1, defaultPaint);
        canvas.drawText(I18n.translate(R.string.army_ui_defense) + ": " + warrior.getDefence(),
                0, imageHeight + ITEM_SIZE * 2, defaultPaint);
        canvas.drawText(I18n.translate(R.string.army_ui_damage) + ": " + warrior.getDamage(),
                0, imageHeight + ITEM_SIZE * 3, defaultPaint);
        canvas.drawText(I18n.translate(R.string.army_ui_fly) + ": " + warrior.isFly(),
                0, imageHeight + ITEM_SIZE * 4, defaultPaint);
        canvas.drawText(I18n.translate(R.string.army_ui_shoot) + ": " + warrior.isShoot(),
                0, imageHeight + ITEM_SIZE * 5, defaultPaint);

        canvas.drawText(I18n.translate("army_names_" + warrior.getTextId()),
                imageWidth + 10, ITEM_SIZE * 1, defaultPaint);
        canvas.drawText(I18n.translate(R.string.army_ui_thereIs) + ": " + shop.getCount(),
                imageWidth + 10, ITEM_SIZE * 3, defaultPaint);
        canvas.drawText(I18n.translate(R.string.army_ui_price) + ": " + warrior.getCostInShop(),
                imageWidth + 10, ITEM_SIZE * 4, defaultPaint);
        canvas.drawText(I18n.translate(R.string.player_attrs_money) + ": " + player.getMoney(),
                getWidth() - imageWidth * 2, ITEM_SIZE * 2, defaultPaint);
    }
}
