package by.siarhei.kb2.app.platforms.android.views;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.helpers.ImageCache;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;

public abstract class RootView {
    protected final MainView mainView;
    protected final I18n i18n;

    public RootView(MainView mainView) {
        this.mainView = mainView;
        this.i18n = Server.getI18n();
    }

    public abstract void draw(@NonNull Canvas canvas, ServerView serverView);

    public boolean onTouchEvent(@NonNull MotionEvent event) {
        Request request = new Request();
        request.setAction(Request.ACTION_OK);
        Server.getServer().request(request);
        return true;
    }

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

    protected int getWidth() { return mainView.getWidth(); }
}
