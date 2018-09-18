package by.siarhei.kb2.app.platforms.android.helpers;

import android.view.MotionEvent;

public class Click {
    private final MotionEvent event;
    private final int xOffset;
    private final int yOffset;
    private final int width;
    private final int height;

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

    public int getX() {
        return (int) event.getX() - xOffset;
    }

    public int getY() {
        return (int) event.getY() - yOffset;
    }
}
