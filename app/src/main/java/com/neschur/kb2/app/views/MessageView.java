package com.neschur.kb2.app.views;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.neschur.kb2.app.controllers.MessageController;

/**
 * Created by siarhei on 12.1.15.
 */
public class MessageView extends SurfaceView implements SurfaceHolder.Callback {
        public static final int ITEM_SIZE = 60;

        private MessageController messageController;
        private DrawThread drawThread;

        public MessageView(Context context, MessageController messageController) {
            super(context);
            getHolder().addCallback(this);
            this.messageController = messageController;
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            drawThread = new DrawThread(getHolder(), messageController);
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
            messageController.closeMessage();
            drawThread.refresh();
            return super.onTouchEvent(event);
        }
    }

