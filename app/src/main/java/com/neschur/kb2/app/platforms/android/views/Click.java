package com.neschur.kb2.app.platforms.android.views;

import android.view.MotionEvent;

public class Click {
    private MotionEvent event;
    private int xOffset;
    private int yOffset;
    private int width;
    private int height;

    public Click(MotionEvent event, int xOffset, int yOffset, int width, int height) {
        this.event = event;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.width = width;
        this.height = height;
    }

    public boolean in(int left, int right, int top, int bottom, int align) {
        if (align == Painter.ALIGN_RIGHT + Painter.ALIGN_BOTTOM) {
            if (event.getX() > width - xOffset - left &&
                    event.getX() < width - xOffset - right &&
                    event.getY() > height - yOffset - top &&
                    event.getY() < height - yOffset - bottom) {
                return true;
            }
        }
        return false;
    }
}
