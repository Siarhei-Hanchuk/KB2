package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.ui.messages.Message;

/**
 * Created by siarhei on 12.1.15.
 */
public class MessageView extends SurfaceView implements SurfaceHolder.Callback, Drawable {
    private Message message;
    private DrawThread drawThread;
    private GameController gameController;
    private Paint paint;
    private ViewClosable closeCallback;

    public MessageView(Context context, Message message, GameController gameController, ViewClosable closeCallback) {
        super(context);
        this.gameController = gameController;
        this.closeCallback = closeCallback;
        getHolder().addCallback(this);
        this.message = message;

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder(), this);
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        closeMessage();
        drawThread.refresh();
        return super.onTouchEvent(event);
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        canvas.drawText(message.getText(), 10, 100, paint);
    }

    public void closeMessage() {
        message.action();
        closeCallback.viewClose();
    }
}

