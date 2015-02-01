package com.neschur.kb2.app.controllers;

import android.app.Activity;
import android.view.SurfaceView;

import com.neschur.kb2.app.Storage;
import com.neschur.kb2.app.views.ViewFactory;

public class MainMenuControllerImpl extends ApplicationController implements MainMenuController{
    private MainController mainController;

    public MainMenuControllerImpl(Activity activity, MainController mainController) {
        super(activity);
        this.mainController = mainController;

        setContentView(ViewFactory.getMainMenuView(this));
    }

    @Override
    public boolean isCurrentGame() {
        return getGameController() != null;
    }

    @Override
    public void newGame() {
        setGameController(new GameController(mainController, GameController.MODE_GAME));
        setMainView(ViewFactory.getMainView(new MainViewControllerImpl(activity)));
    }

    @Override
    public void newTraining() {
        setGameController(new GameController(mainController, GameController.MODE_TRAINING));
        setMainView(ViewFactory.getMainView(new MainViewControllerImpl(activity)));
    }

    @Override
    public void loadGame() {
        Storage storage = new Storage(activity);
        GameController gameController = storage.loadGame("save1");
        mainController.setGameController(gameController);
        SurfaceView mainView = ViewFactory.getMainView(new MainViewControllerImpl(activity));
        setMainView(mainView);
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

    private void setMainView(SurfaceView view) {
        mainController.setMainView(view);
    }
}
