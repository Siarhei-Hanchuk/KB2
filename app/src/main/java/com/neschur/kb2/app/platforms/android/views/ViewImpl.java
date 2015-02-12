package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.View;
import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.models.GameGrid;

public abstract class ViewImpl extends SurfaceView implements SurfaceHolder.Callback, Drawable, View {
    private static final int IMAGE_WIDTH = 96;
    private static final int IMAGE_HEIGHT = 82;

    final ViewController viewController;
    final I18n i18n;
    DrawThread drawThread;
    Painter painter;
    Click click;
    Paint defaultPaint = null;

    ViewImpl(Context context, ViewController viewController) {
        super(context);
        getHolder().addCallback(this);
        this.viewController = viewController;
        this.i18n = viewController.i18n();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

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

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        int[] offsets = calcOffsets();
        click = new Click(event, offsets[0], offsets[1], getWidth(), getHeight());
        return false;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int[] offsets = calcOffsets();
        painter = new Painter(canvas, offsets[0], offsets[1], getWidth(), getHeight());
        canvas.drawColor(Color.BLACK);
    }

    @Override
    public void refresh() {
        drawThread.refresh();
    }

    @Override
    public void refresh(int delay) {
        if (delay > 0) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
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
        return ((int) (IMAGE_WIDTH * getScale())) * x + calcOffsets()[0];
    }

    int stepY(int y) {
        return ((int) (IMAGE_HEIGHT * getScale())) * y + calcOffsets()[1];
    }

    int textHeight() {
        return (int) (menuItemHeight() * 0.6);
    }

    int menuItemHeight() {
        return (int) (getHeight() / 8.0);
    }

    Paint getDefaultPaint() {
        if (defaultPaint == null) {
            defaultPaint = new Paint();
            defaultPaint.setColor(Color.WHITE);
            defaultPaint.setTextSize(textHeight());
        }
        return defaultPaint;
    }

    private int[] calcOffsets() {
        double scaleX = (double) getWidth() / (IMAGE_WIDTH * GameGrid.STEP_X);
        double scaleY = (double) getHeight() / (IMAGE_HEIGHT * GameGrid.STEP_Y);
        int[] offsets = new int[2];
        if (scaleX > scaleY) {
            offsets[0] = (getWidth() - stepX() * GameGrid.STEP_X) / 2;
        } else {
            offsets[0] = (getHeight() - stepX() * GameGrid.STEP_Y) / 2;
        }

        return offsets;
    }

    ImageCache getImageCache() {
        return ImageCache.getInstance(getResources(), stepX(), stepY());
    }

}
