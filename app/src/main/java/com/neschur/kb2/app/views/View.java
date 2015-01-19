package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.models.Player;

/**
 * Created by siarhei on 19.1.15.
 */
public abstract class View extends SurfaceView implements SurfaceHolder.Callback, Drawable {
    protected ViewClosable closeCallback;
    protected GameController gameController;
    protected Player player;
    protected DrawThread drawThread;
    protected Paint defaultPaint;

    public View(Context context, GameController gameController, ViewClosable closeCallback) {
        super(context);
        getHolder().addCallback(this);
        this.closeCallback = closeCallback;

        this.gameController = gameController;
        this.player = gameController.getPlayer();

        defaultPaint = new Paint();
        defaultPaint.setColor(Color.WHITE);
        defaultPaint.setStyle(Paint.Style.FILL);
        defaultPaint.setTextSize(50);
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
}
