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
import by.siarhei.kb2.app.platforms.android.drawers.Drawer;
import by.siarhei.kb2.app.platforms.android.drawers.DrawerFactory;
import by.siarhei.kb2.app.platforms.android.touchers.ArmyShopToucher;
import by.siarhei.kb2.app.platforms.android.touchers.GameToucher;
import by.siarhei.kb2.app.platforms.android.helpers.Click;
import by.siarhei.kb2.app.platforms.android.helpers.DrawThread;
import by.siarhei.kb2.app.platforms.android.helpers.Drawable;
import by.siarhei.kb2.app.platforms.android.helpers.ImageCache;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.platforms.android.touchers.Menu2Toucher;
import by.siarhei.kb2.app.server.GameDispatcher;
import by.siarhei.kb2.app.server.GameGrid;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;
import by.siarhei.kb2.app.ui.menus.Menu;

public class XMainView extends SurfaceView implements SurfaceHolder.Callback, Drawable, View {
    private static final int IMAGE_WIDTH = 96;
    private static final int IMAGE_HEIGHT = 82;

    private DrawThread drawThread;
    private Paint defaultPaint = null;
    protected Context context;

    public XMainView(Context context) {
        super(context);
        this.context = context;
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

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

    @Override
    public void refresh(int delay) {
        if (delay > 0) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        drawThread.refresh();
    }

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

    public int[] calcOffsets() {
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
        ServerView serverView = Server.getServer().getView();
        Request request = new Request();

        switch (serverView.getViewMode()) {
            case GameDispatcher.VIEW_MODE_GRID:
                GameToucher.touchEvent(event, this);
                break;
            case GameDispatcher.VIEW_MODE_MENU:
                Menu menu = Server.getServer().getView().getMenu();
                double y = event.getY();
                int item = (int) y / menuItemHeight();

                int count = menu.getCount();
                if(menu.withExit()) {
                    count++;
                }
                if (item < count) {
                    request.setAction(Request.ACTION_SELECT);
                    request.setMenuItem(item);
                }

                Server.getServer().request(request);
                break;
            case GameDispatcher.VIEW_MODE_ARMY_SHOP:
                ArmyShopToucher.touchEvent(event, this);
                break;
            case GameDispatcher.VIEW_MODE_MENU2:
                Menu2Toucher.touchEvent(event, this);
                break;
            default:
                request.setAction(Request.ACTION_OK);
                Server.getServer().request(request);
                break;
        }

        refresh();
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);

        ServerView serverView = Server.getServer().getView();
        DrawerFactory drawerFactory = new DrawerFactory(canvas, this);
        Drawer drawer = drawerFactory.getDrawer(serverView.getViewMode());

        drawer.draw(serverView);
    }
}
