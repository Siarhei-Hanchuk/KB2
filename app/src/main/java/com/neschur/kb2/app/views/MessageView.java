package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.ui.messages.Message;

/**
 * Created by siarhei on 12.1.15.
 */
public class MessageView extends View {
    private Message message;

    public MessageView(Context context, Message message, GameController gameController, ViewClosable closeCallback) {
        super(context, gameController, closeCallback);
        this.message = message;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        closeMessage();
        drawThread.refresh();
        return super.onTouchEvent(event);
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        canvas.drawText(message.getText(), 10, 100, defaultPaint);
    }

    public void closeMessage() {
        message.action();
        closeCallback.viewClose();
    }
}

