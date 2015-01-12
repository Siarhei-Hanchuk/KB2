package com.neschur.kb2.app.views;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by siarhei on 1.7.14.
 */
class DrawThread extends Thread {
    private boolean running = false;
    private boolean refresh = true;
    private SurfaceHolder surfaceHolder;
    private Drawable drawable;

    public DrawThread(SurfaceHolder surfaceHolder, Drawable drawable) {
        this.surfaceHolder = surfaceHolder;
        this.drawable = drawable;
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
            if (!refresh)
                continue;
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas(null);
                if (canvas == null)
                    continue;
                drawable.draw(canvas);
                refresh = false;
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}

