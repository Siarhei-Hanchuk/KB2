package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.Storage;
import com.neschur.kb2.app.models.Game;
import com.neschur.kb2.app.views.View;

public abstract class ApplicationController implements ViewController, GameOwner {
    private static Game game;
    private static AppControllerImpl appControllerImpl;
    private static Storage storage;

    public static void initApp(AppControllerImpl _appControllerImpl, Storage _storage) {
        appControllerImpl = _appControllerImpl;
        storage = _storage;
    }

    @Override
    public Game getGame() {
        return game;
    }

    protected void setGame(Game game) {
        ApplicationController.game = game;
    }

    protected void setContentView(View view) {
        appControllerImpl.setContentView(view);
    }

    protected Storage getStorage() {
        return storage;
    }

    public void exit() {
        appControllerImpl.exit();
    }
}
