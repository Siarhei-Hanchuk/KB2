package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.ArmyShop;

/**
 * Created by siarhei on 18.1.15.
 */
public class ArmyShopView extends View {
    private ArmyShop shop;

    public ArmyShopView(Context context, ArmyShop shop, GameController gameController, ViewClosable closeCallback) {
        super(context, gameController, closeCallback);

        this.shop = shop;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        closeCallback.viewClose();
        drawThread.refresh();
        return super.onTouchEvent(event);
    }

    public void draw(Canvas canvas) {
        Bitmap image = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        getContext().getResources(), shop.getWarrior().getId()
                ),
                getWidth() / 6, getHeight() / 5, false
        );

        canvas.drawBitmap(image, 0, 0, null);
        canvas.drawText("Item1: " + "value", getWidth() / 6, getHeight() / 5, defaultPaint);
        canvas.drawText("Item2: " + "value", getWidth() / 6, getHeight() / 5 + ITEM_SIZE, defaultPaint);
    }
}
