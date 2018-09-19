package by.siarhei.kb2.app.platforms.android.views;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.platforms.android.XMainView;
import by.siarhei.kb2.app.platforms.android.helpers.ImageCache;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;

public abstract class RootView {
    protected final Canvas canvas;
    protected final XMainView mainView;
    protected final I18n i18n;

    public RootView(Canvas canvas, XMainView mainView) {
        this.canvas = canvas;
        this.mainView = mainView;
        this.i18n = Server.getI18n();
    }

    public abstract void draw(ServerView serverView);

    public abstract void onTouchEvent(@NonNull MotionEvent event);

    protected Painter getPainter(Canvas canvas) {
        return mainView.getPainter(canvas);
    }

    protected ImageCache getImageCache() {
        return mainView.getImageCache();
    }

    protected int stepX() {
        return mainView.stepX();
    }

    protected int stepY() {
        return mainView.stepY();
    }

    protected Paint getDefaultPaint() {
        return mainView.getDefaultPaint();
    }

    protected int textHeight() {
        return mainView.textHeight();
    }

    protected int menuItemHeight() {
        return mainView.menuItemHeight();
    }

    protected Resources getResources() {
        return mainView.getResources();
    }
}
