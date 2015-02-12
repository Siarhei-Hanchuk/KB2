package com.neschur.kb2.app.platforms.android.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Painter {
    public static final int ALIGN_NONE = 0;
    public static final int ALIGN_RIGHT = 1;
    public static final int ALIGN_BOTTOM = 2;
    private Canvas canvas;
    private int xOffset;
    private int yOffset;
    private int width;
    private int height;

    public Painter(Canvas canvas, int xOffset, int yOffset, int width, int height) {
        this.canvas = canvas;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.width = width;
        this.height = height;
    }

    public void drawText(String text, float x, float y, Paint paint) {
        drawText(text, x, y, paint, ALIGN_NONE);
    }

    public void drawText(String text, float x, float y, Paint paint, int align) {
        if (align == ALIGN_RIGHT)
            canvas.drawText(text, width - x - xOffset, y + yOffset, paint);
        else if (align == ALIGN_BOTTOM)
            canvas.drawText(text, x + xOffset, height - y - yOffset, paint);
        else if (align == ALIGN_BOTTOM + ALIGN_RIGHT)
            canvas.drawText(text, width - x - xOffset, height - y - yOffset, paint);
        else
            canvas.drawText(text, x + xOffset, y + yOffset, paint);
    }

    public void drawBitmap(Bitmap bitmap, float x, float y) {
        canvas.drawBitmap(bitmap, xOffset + x, yOffset + y, null);
    }

    public void drawRect(float x, float y, float top, float right, Paint paint) {
        drawRect(x, y, top, right, paint, ALIGN_NONE);
    }

    public void drawRect(float left, float top, float right, float bottom, Paint paint, int align) {
        if (align == ALIGN_RIGHT) {
            canvas.drawRect(width - xOffset - left, top,
                    width - xOffset - right, bottom, paint);
        } else if (align == ALIGN_BOTTOM) {
            canvas.drawRect(left + xOffset, height - yOffset - top,
                    right, height - yOffset - bottom, paint);
        } else if (align == ALIGN_BOTTOM + ALIGN_RIGHT) {
            canvas.drawRect(width - xOffset - left, height - yOffset - top,
                    width - xOffset - right, height - yOffset - bottom, paint);
        }
    }
}
