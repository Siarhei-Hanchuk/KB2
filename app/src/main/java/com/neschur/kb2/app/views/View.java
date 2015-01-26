package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.ui.ImageCache;

public abstract class View extends SurfaceView implements SurfaceHolder.Callback, Drawable {
    protected ViewClosable closeCallback;
    protected GameController gameController;
    protected Player player;
    protected DrawThread drawThread;
    protected ImageCache imageCache;
    protected int xOffset = 0;
    protected int yOffset = 0;

    public View(Context context, GameController gameController, ViewClosable closeCallback) {
        super(context);
        getHolder().addCallback(this);
        this.closeCallback = closeCallback;

        this.gameController = gameController;
        if (gameController != null)
            this.player = gameController.getPlayer();
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
                e.printStackTrace();
            }
        }
    }

    protected double getScale() {
        double scaleX = (double) getWidth() / (96 * 6);
        double scaleY = (double) getHeight() / (82 * 5);
        return (scaleX > scaleY) ? scaleY : scaleX;
    }

    protected int stepX() {
        return (int) (96 * getScale());
    }

    protected int stepY() {
        return (int) (82 * getScale());
    }

    protected int textHeight() {
        return (int) (menuItemHeight() * 0.8);
    }

    protected int menuItemHeight() {
        return (int) (getHeight() / 8.0);
    }

    protected Paint getDefaultPaint() {
        Paint defaultPaint = new Paint();
        defaultPaint.setColor(Color.WHITE);
        defaultPaint.setTextSize(textHeight());
        return defaultPaint;
    }

    protected void calcOffsets() {
        double scaleX = (double) getWidth() / (96 * 6);
        double scaleY = (double) getHeight() / (82 * 5);
        if (scaleX > scaleY) {
            xOffset = (getWidth() - stepX() * 6) / 2;
        } else {
            yOffset = (getHeight() - stepX() * 5) / 2;
        }
    }
}
