package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.Storage;
import com.neschur.kb2.app.models.Game;
import com.neschur.kb2.app.views.View;
import com.neschur.kb2.app.views.ViewFactory;

public abstract class ApplicationController implements ViewController, GameOwner {
    private static PlatformController platformController;
    private static Storage storage;
    private static ViewFactory viewFactory;
    private static Game game;

    public static void initApp(PlatformController _platformController) {
        platformController = _platformController;
        storage = _platformController.getStorage();
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
}
