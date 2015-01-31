package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.models.GameGrid;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.ImageCache;

public abstract class View extends SurfaceView implements SurfaceHolder.Callback, Drawable {
    public static final int IMAGE_WIDTH = 96;
    public static final int IMAGE_HEIGHT = 82;

    protected ViewClosable closeCallback;
    protected GameController gameController;
    protected Player player;
    protected DrawThread drawThread;
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
        double scaleX = (double) getWidth() / (IMAGE_WIDTH * GameGrid.STEP_X);
        double scaleY = (double) getHeight() / (IMAGE_HEIGHT * GameGrid.STEP_Y);
        return (scaleX > scaleY) ? scaleY : scaleX;
    }

    protected int stepX() {
        return (int) (IMAGE_WIDTH * getScale());
    }

    protected int stepY() {
        return (int) (IMAGE_HEIGHT * getScale());
    }

    protected int stepX(int x) {
        return ((int) (IMAGE_WIDTH * getScale())) * x + xOffset;
    }

    protected int stepY(int y) {
        return ((int) (IMAGE_HEIGHT * getScale())) * y + yOffset;
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
        double scaleX = (double) getWidth() / (IMAGE_WIDTH * GameGrid.STEP_X);
        double scaleY = (double) getHeight() / (IMAGE_HEIGHT * GameGrid.STEP_Y);
        if (scaleX > scaleY) {
            xOffset = (getWidth() - stepX() * GameGrid.STEP_X) / 2;
        } else {
            yOffset = (getHeight() - stepX() * GameGrid.STEP_Y) / 2;
        }
    }

    protected ImageCache getImageCache() {
        return ImageCache.getInstance(getResources(), stepX(), stepY());
    }
}
