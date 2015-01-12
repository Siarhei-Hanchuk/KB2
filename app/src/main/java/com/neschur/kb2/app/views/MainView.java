package com.neschur.kb2.app.views;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.neschur.kb2.app.controllers.MainController;

/**
* Created by siarhei on 1.7.14.
*/
public class MainView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread drawThread;
    private MainController mainController;

    public MainView(Context context, MainController mainController) {
        super(context);
        getHolder().addCallback(this);
        this.mainController = mainController;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread (getHolder(), mainController);
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
        double centerY = this.getHeight()/2;
        double centerX = (this.getWidth()*5/6)/2;
        if(event.getX() > this.getWidth()*5/6) {
            int item = (int)event.getY()/(this.getHeight()/5);
            mainController.touchMenu(item);
        } else {
            if (event.getY() > centerY && event.getX() > centerX * 0.5 && event.getX() < centerX * 1.5) {
                mainController.touchDown();
            }
            if (event.getY() < centerY && event.getX() > centerX * 0.5 && event.getX() < centerX * 1.5) {
                mainController.touchUp();
            }
            if (event.getX() > centerX && event.getY() > centerY * 0.5 && event.getY() < centerY * 1.5) {
                mainController.touchRight();
            }
            if (event.getX() < centerX && event.getY() > centerY * 0.5 && event.getY() < centerY * 1.5) {
                mainController.touchLeft();
            }
        }
        drawThread.refresh();
        return super.onTouchEvent(event);
    }
}
