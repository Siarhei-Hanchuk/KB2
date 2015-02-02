package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.neschur.kb2.app.platforms.android.ImageCache;
import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.models.GameGrid;

public abstract class View extends SurfaceView implements SurfaceHolder.Callback, Drawable {
    private static final int IMAGE_WIDTH = 96;
    private static final int IMAGE_HEIGHT = 82;

    final ViewController viewController;
    DrawThread drawThread;
    int xOffset = 0;
    int yOffset = 0;

    View(Context context, ViewController viewController) {
        super(context);
        getHolder().addCallback(this);
        this.viewController = viewController;
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

    public void refresh() {
        drawThread.refresh();
    }

    private double getScale() {
        double scaleX = (double) getWidth() / (IMAGE_WIDTH * GameGrid.STEP_X);
        double scaleY = (double) getHeight() / (IMAGE_HEIGHT * GameGrid.STEP_Y);
        return (scaleX > scaleY) ? scaleY : scaleX;
    }

    int stepX() {
        return (int) (IMAGE_WIDTH * getScale());
    }

    int stepY() {
        return (int) (IMAGE_HEIGHT * getScale());
    }

    int stepX(int x) {
        return ((int) (IMAGE_WIDTH * getScale())) * x + xOffset;
    }

    int stepY(int y) {
        return ((int) (IMAGE_HEIGHT * getScale())) * y + yOffset;
    }

    int textHeight() {
        return (int) (menuItemHeight() * 0.8);
    }

    int menuItemHeight() {
        return (int) (getHeight() / 8.0);
    }

    Paint getDefaultPaint() {
        Paint defaultPaint = new Paint();
        defaultPaint.setColor(Color.WHITE);
        defaultPaint.setTextSize(textHeight());
        return defaultPaint;
    }

    void calcOffsets() {
        double scaleX = (double) getWidth() / (IMAGE_WIDTH * GameGrid.STEP_X);
        double scaleY = (double) getHeight() / (IMAGE_HEIGHT * GameGrid.STEP_Y);
        if (scaleX > scaleY) {
            xOffset = (getWidth() - stepX() * GameGrid.STEP_X) / 2;
        } else {
            yOffset = (getHeight() - stepX() * GameGrid.STEP_Y) / 2;
        }
    }

    ImageCache getImageCache() {
        return ImageCache.getInstance(getResources(), stepX(), stepY());
    }
}
