package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.Storage;
import com.neschur.kb2.app.ViewFactory;
import com.neschur.kb2.app.models.Game;
import com.neschur.kb2.app.platforms.android.views.View;

public abstract class ApplicationController implements ViewController, GameOwner {
    private static PlatformController platformController;
    private static Storage storage;
    private static ViewFactory viewFactory;
    private static Game game;
    private static I18n i18n;

    public static void initApp(PlatformController _platformController) {
        platformController = _platformController;
        storage = _platformController.getStorage();
        i18n = _platformController.getI18n();
    }

    protected ViewFactory getViewFactory() {
        if (viewFactory == null)
            viewFactory = platformController.getViewFactory(this);
        return viewFactory;
    }

    @Override
    public Game getGame() {
        return game;
    }

    protected void setGame(Game game) {
        ApplicationController.game = game;
    }

    protected void setContentView(View view) {
        platformController.setContentView(view);
    }

    protected Storage getStorage() {
        return storage;
    }

    public void exit() {
        platformController.exit();
    }

    public I18n i18n() {
        return i18n;
    }
}
