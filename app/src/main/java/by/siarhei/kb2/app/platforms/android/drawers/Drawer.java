package by.siarhei.kb2.app.platforms.android.drawers;

import android.graphics.Canvas;

import by.siarhei.kb2.app.platforms.android.XMainView;
import by.siarhei.kb2.app.server.ServerView;

public abstract class Drawer {
    protected final Canvas canvas;
    protected final XMainView mainView;

    public Drawer(Canvas canvas, XMainView mainView) {
        this.canvas = canvas;
        this.mainView = mainView;
    }

    public abstract void draw(ServerView serverView);
}
