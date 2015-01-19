package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.neschur.kb2.app.R;

/**
 * Created by siarhei on 18.1.15.
 */
public class ArmyShopView extends SurfaceView implements SurfaceHolder.Callback, Drawable {
    private ViewClosable closeCallback;
    private DrawThread drawThread;
//    private Player player;

    public ArmyShopView(Context context, ViewClosable closeCallback) {
        super(context);
        getHolder().addCallback(this);
        this.closeCallback = closeCallback;
//        this.player = mainController.getGameController().getPlayer();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder(), this);
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
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
