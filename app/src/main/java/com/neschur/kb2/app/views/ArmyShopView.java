package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;

/**
 * Created by siarhei on 18.1.15.
 */
public class ArmyShopView extends View {
    private ViewClosable closeCallback;
    private DrawThread drawThread;

    public ArmyShopView(Context context, GameController gameController, ViewClosable closeCallback) {
        super(context, gameController, closeCallback);
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
                        getContext().getResources(), R.drawable.army_boar
                ),
                getWidth() / 6, getHeight() / 5, false
        );
        canvas.drawBitmap(image, 0, 0, null);
    }
}
