package com.neschur.kb2.app.views;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.neschur.kb2.app.controllers.MainController;

/**
* Created by siarhei on 1.7.14.
*/
class DrawThread extends Thread {
    private boolean running = false;
    private boolean refresh = false;
    private SurfaceHolder surfaceHolder;
    private MainController mainController;

    public DrawThread(SurfaceHolder surfaceHolder, MainController mainController) {
        this.surfaceHolder = surfaceHolder;
        this.mainController = mainController;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void refresh() {
        this.refresh = true;
    }

    @Override
    public void run() {
        Canvas canvas;
        while (running) {
            if(!refresh)
                continue;
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas(null);
                if (canvas == null)
                    continue;
                mainController.rePaint(canvas);
                refresh = false;
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}

