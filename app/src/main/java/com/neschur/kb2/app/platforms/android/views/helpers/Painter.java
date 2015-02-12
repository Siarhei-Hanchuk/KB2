package com.neschur.kb2.app.platforms.android.views.helpers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Painter {
    public static final int ALIGN_RIGHT = 1;
    public static final int ALIGN_BOTTOM = 2;
    private static final int ALIGN_NONE = 0;
    private final Canvas canvas;
    private final int xOffset;
    private final int yOffset;
    private final int width;
    private final int height;

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

    public void drawRect(float left, float top, float right, float bottom, Paint paint) {
        drawRect(left, top, right, bottom, paint, ALIGN_NONE);
    }

    public void drawRect(float left, float top, float right, float bottom, Paint paint, int align) {
        if (align == ALIGN_RIGHT) {
            canvas.drawRect(width - xOffset - left, top + yOffset,
                    width - xOffset - right, bottom + yOffset, paint);
        } else if (align == ALIGN_BOTTOM) {
            canvas.drawRect(left + xOffset, height - yOffset - top,
                    right + xOffset, height - yOffset - bottom, paint);
        } else if (align == ALIGN_BOTTOM + ALIGN_RIGHT) {
            canvas.drawRect(width - xOffset - left, height - yOffset - top,
                    width - xOffset - right, height - yOffset - bottom, paint);
        } else {
            canvas.drawRect(left + xOffset, top + yOffset,
                    right + xOffset, bottom + yOffset, paint);
        }
    }
}
