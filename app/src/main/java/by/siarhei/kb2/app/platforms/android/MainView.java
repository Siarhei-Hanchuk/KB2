package by.siarhei.kb2.app.platforms.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import by.siarhei.kb2.app.View;
import by.siarhei.kb2.app.platforms.android.views.MainMenuView;
import by.siarhei.kb2.app.platforms.android.views.ViewFactory;
import by.siarhei.kb2.app.platforms.android.helpers.Click;
import by.siarhei.kb2.app.platforms.android.helpers.DrawThread;
import by.siarhei.kb2.app.platforms.android.helpers.Drawable;
import by.siarhei.kb2.app.platforms.android.helpers.ImageCache;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.platforms.android.views.RootView;
import by.siarhei.kb2.app.server.GameGrid;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.Response;

public class MainView extends SurfaceView implements SurfaceHolder.Callback, Drawable, View {
    private static final int IMAGE_WIDTH = 96;
    private static final int IMAGE_HEIGHT = 82;

    private DrawThread drawThread;
    private Paint defaultPaint = null;
    private boolean menuMode = true;

    private RootView view;

    public MainView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder(), this);
        drawThread.setRunning(true);
        drawThread.start();

        Sound.setContext(getContext());
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
                e.printStackTrace();
            }
        }
    }

    public Click getClick(@NonNull MotionEvent event) {
        int[] offsets = calcOffsets();
        return new Click(event, offsets[0], offsets[1], getWidth(), getHeight());
    }

    public Painter getPainter(@NonNull Canvas canvas) {
        int[] offsets = calcOffsets();
        return new Painter(canvas, offsets[0], offsets[1], getWidth(), getHeight());
    }

    @Override
    public void refresh() {
        drawThread.refresh();
    }

//    @Override
//    public void refresh(int delay) {
//        if (delay > 0) {
//            try {
//                Thread.sleep(delay);
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//        }
//        drawThread.refresh();
//    }

    private double getScale() {
        double scaleX = (double) getWidth() / (IMAGE_WIDTH * GameGrid.STEP_X);
        double scaleY = (double) getHeight() / (IMAGE_HEIGHT * GameGrid.STEP_Y);
        return (scaleX > scaleY) ? scaleY : scaleX;
    }

    public int stepX() {
        return (int) (IMAGE_WIDTH * getScale());
    }

    public int stepY() {
        return (int) (IMAGE_HEIGHT * getScale());
    }

    public int textHeight() {
        return (int) (menuItemHeight() * 0.6);
    }

    public int menuItemHeight() {
        return (int) (getHeight() / 8.0);
    }

    public Paint getDefaultPaint() {
        if (defaultPaint == null) {
            defaultPaint = new Paint();
            defaultPaint.setColor(Color.WHITE);
            defaultPaint.setTextSize(textHeight());
        }
        return defaultPaint;
    }

    private int[] calcOffsets() {
        double scaleX = (double) getWidth() / (IMAGE_WIDTH * GameGrid.STEP_X);
        double scaleY = (double) getHeight() / (IMAGE_HEIGHT * GameGrid.STEP_Y);
        int[] offsets = new int[2];
        if (scaleX > scaleY) {
            offsets[0] = (getWidth() - stepX() * GameGrid.STEP_X) / 2;
        } else {
            offsets[0] = (getHeight() - stepX() * GameGrid.STEP_Y) / 2;
        }

        return offsets;
    }

    public ImageCache getImageCache() {
        return ImageCache.getInstance(getResources(), stepX(), stepY());
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        boolean needRefresh = getView().onTouchEvent(event);

        if(needRefresh) {
            view = null;
            menuMode = false;
        }
        refresh();

        // Ugly hack for battle animation
        class MyThread extends Thread {
            public void run(){
                while(!Server.getServer().getResponse().playerControl()) {
                    try {
                        MyThread.sleep(1000);
                    } catch (InterruptedException e) { }
                    getView().onTouchEvent(event);
                    refresh();
                }
            }
        }
        if(!Server.getServer().getResponse().playerControl()) {
            MyThread myThread = new MyThread();
            myThread.start();
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);

        Response response = null;
        if(!menuMode)
            response = Server.getServer().getResponse();

        getView().draw(canvas, response);
    }

    private RootView getView() {
        if(menuMode) {
            return new MainMenuView(this);
        }
        if(view != null) {
            return view;
        }
        Response response = Server.getServer().getResponse();
        ViewFactory viewFactory = new ViewFactory(this);
        view = viewFactory.getView(response.getViewMode());
        return view;
    }

    public void switchOnMenu() {
        menuMode = true;
        refresh();
    }
}
