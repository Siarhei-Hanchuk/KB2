package by.siarhei.kb2.app.platforms.android.views;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.NonNull;

import android.view.KeyEvent;
import android.view.MotionEvent;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.helpers.ImageCache;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.Response;

public abstract class RootView {
    final MainView mainView;
    final I18n i18n;

    RootView(MainView mainView) {
        this.mainView = mainView;
        this.i18n = Server.getI18n();
    }

    public abstract void draw(@NonNull Canvas canvas, Response response);

    public boolean onTouchEvent(@NonNull MotionEvent event) {
        Request request = new Request();
        request.setAction(Request.ACTION_OK);
        Server.getServer().request(request);
        return true;
    }

    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        return false;
    }

    Painter getPainter(Canvas canvas) {
        return mainView.getPainter(canvas);
    }

    ImageCache getImageCache() {
        return mainView.getImageCache();
    }

    int stepX() {
        return mainView.stepX();
    }

    int stepY() {
        return mainView.stepY();
    }

    Paint getDefaultPaint() {
        return mainView.getDefaultPaint();
    }

    int textHeight() {
        return mainView.textHeight();
    }

    int menuItemHeight() {
        return mainView.menuItemHeight();
    }

    Resources getResources() {
        return mainView.getResources();
    }

    int getWidth() { return mainView.getWidth(); }

    int getHeight() { return mainView.getHeight(); }
}
