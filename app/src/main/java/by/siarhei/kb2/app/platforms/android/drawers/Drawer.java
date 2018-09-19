package by.siarhei.kb2.app.platforms.android.drawers;

import android.graphics.Canvas;
import android.graphics.Paint;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.platforms.android.XMainView;
import by.siarhei.kb2.app.platforms.android.helpers.ImageCache;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;

public abstract class Drawer {
    protected final Canvas canvas;
    protected final XMainView mainView;
    protected final I18n i18n;

    public Drawer(Canvas canvas, XMainView mainView) {
        this.canvas = canvas;
        this.mainView = mainView;
        this.i18n = Server.getI18n();
    }

    public abstract void draw(ServerView serverView);

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
}
