package com.neschur.kb2.app.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.ui.messages.Message;

public class MessageView extends View {
    private Message message;

    public MessageView(ViewController viewController, Message message) {
        super(viewController);
        this.message = message;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        closeMessage();
        drawThread.refresh();
        return super.onTouchEvent(event);
    }

    public void draw(@NonNull Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        canvas.drawText(message.getText(), 10, 100, getDefaultPaint());
    }

    public void closeMessage() {
        message.action();
        viewController.viewClose();
    }
}

