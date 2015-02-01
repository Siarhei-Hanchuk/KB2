package com.neschur.kb2.app.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.SurfaceView;

public abstract class ApplicationController implements ViewController, GameControllerOwner {
    protected Activity activity;
    private static GameController gameController;

    public ApplicationController(Activity activity) {
        this.activity = activity;
    }

    public void setContentView(SurfaceView view) {
        activity.setContentView(view);
    }

    @Override
    public Context getContext() {
        return activity;
    }

    @Override
    public GameController getGameController() {
        return gameController;
    }

//    protected ViewFactory getViewFactory() {
//        return new ViewFactory(null);
//    }
//

    protected void setGameController(GameController gameController) {
        ApplicationController.gameController = gameController;
    }
}
