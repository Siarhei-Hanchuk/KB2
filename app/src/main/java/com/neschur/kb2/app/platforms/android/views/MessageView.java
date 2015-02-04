package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.ui.messages.Message;

class MessageView extends ViewImpl {
    private final Message message;

    public MessageView(Context context, ViewController viewController, Message message) {
        super(context, viewController);
        this.message = message;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        closeMessage();
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        String text = message.getText();
        float textLength = getDefaultPaint().measureText(text);
        if (textLength < getWidth()) {
            canvas.drawText(message.getText(), 10, 100, getDefaultPaint());
        } else {
            int count = (int)(text.length() / (textLength / getWidth()));
            int length = (text.length() + count - 1) / count;
            for (int ix = 0, pos = 0; ix < length; ix++, pos += count) {
                canvas.drawText(text.substring(pos, Math.min(text.length(), pos + count)).trim()
                        , 10, 100 * (ix + 1), getDefaultPaint());
            }
        }
    }

    private void closeMessage() {
        message.action();
        viewController.viewClose();
    }
}

