package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.ImageCache;
import com.neschur.kb2.app.models.Magics;

public class MagicView extends View {
    private Magics magics;
    private int mode = 0;

    public MagicView(Context context, Magics magics, ViewClosable closeCallback) {
        super(context, closeCallback);
        this.magics = magics;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        double y = event.getY();
        int item = (int) y / menuItemHeight();

        if (item == 4) {
            mode = 1;
        } else {
            closeCallback.viewClose();
        }
        drawThread.refresh();
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        for (int i = 1; i < 8; i++) {
            canvas.drawText(I18n.translate("magic_hiking_magic" + i) + ": "
                            + magics.getMagic(i - 1),
                    0, i * textHeight(), getDefaultPaint());
        }
    }
}
