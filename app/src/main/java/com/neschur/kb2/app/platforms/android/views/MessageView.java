package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.ui.messages.Message;

class MessageView extends View {
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

        canvas.drawText(message.getText(), 10, 100, getDefaultPaint());
    }

    private void closeMessage() {
        message.action();
        viewController.viewClose();
    }
}

