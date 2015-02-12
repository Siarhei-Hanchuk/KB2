package com.neschur.kb2.app.platforms.android.views.helpers;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {
    private final SurfaceHolder surfaceHolder;
    private final Drawable drawable;
    private boolean running = false;
    private boolean refresh = true;

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

