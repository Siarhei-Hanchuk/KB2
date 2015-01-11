package com.neschur.kb2.app.views;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.neschur.kb2.app.controllers.MenuController;

/**
 * Created by siarhei on 11.1.15.
 */
public class MenuView extends SurfaceView implements SurfaceHolder.Callback {
    public static final int ITEM_SIZE = 60;

    private MenuController menuController;
    private DrawThread drawThread;

    public MenuView(Context context, MenuController menuController) {
        super(context);
        getHolder().addCallback(this);
        this.menuController = menuController;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread (getHolder(), menuController);
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
        double y = event.getY();
        int item = (int)y/ITEM_SIZE;
        menuController.select(item);
        drawThread.refresh();
        return super.onTouchEvent(event);
    }
}
