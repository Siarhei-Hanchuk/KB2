package com.neschur.kb2.app.controllers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.views.Drawable;
import com.neschur.kb2.app.views.MessageView;

/**
 * Created by siarhei on 12.1.15.
 */
public class MessageController implements Drawable {
    private MainActivity activity;
    private MainController mainController;
    private MessageView view;
    private String message;
    private Paint paint;

    public MessageController(MainActivity activity, MainController mainController) {
        this.activity = activity;
        this.mainController = mainController;

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        canvas.drawText(message, 10, 100, paint);
    }

    public void updateMessage(String message) {
        this.message = message;
    }

    public MessageView getView() {
        if (view == null)
            view = new MessageView(activity, this);
        return view;
    }

    public void closeMessage() {
        mainController.closeMenu();
    }
}
