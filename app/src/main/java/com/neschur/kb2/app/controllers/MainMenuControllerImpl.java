package com.neschur.kb2.app.controllers;

import android.app.Activity;

import com.neschur.kb2.app.Storage;
import com.neschur.kb2.app.models.Game;
import com.neschur.kb2.app.views.ViewFactory;

public class MainMenuControllerImpl extends ApplicationController implements MainMenuController {
    public MainMenuControllerImpl(Activity activity) {
        super(activity);
        setContentView(ViewFactory.getMainMenuView(this));
    }

    @Override
    public boolean isCurrentGame() {
        return getGameController() != null;
    }

    @Override
    public void newGame() {
        setGameController(new Game(new MainViewControllerImpl(activity), 1));

    }

    @Override
    public void newTraining() {
        setGameController(new Game(new MainViewControllerImpl(activity), 0));
    }

    @Override
    public void loadGame() {
        Storage storage = new Storage(activity);
        Game game = storage.loadGame("save1");
        setGameController(game);
        new MainViewControllerImpl(activity);
    }

    @Override
    public void saveGame() {
        Storage storage = new Storage(activity);
        storage.saveGame(getGameController(), "save1");
    }

    @Override
    public void exit() {
        activity.finish();
        System.exit(0);
    }

    @Override
    public void viewClose() {
        setContentView(ViewFactory.getMainView(new MainViewControllerImpl(activity)));
    }
}
