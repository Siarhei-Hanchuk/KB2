package com.neschur.kb2.app.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.SurfaceView;

import com.neschur.kb2.app.models.Game;

public abstract class ApplicationController implements ViewController, GameControllerOwner {
    private static Game game;
    protected Activity activity;

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
    public Game getGameController() {
        return game;
    }

//    protected ViewFactory getViewFactory() {
//        return new ViewFactory(null);
//    }
//

    protected void setGameController(Game game) {
        ApplicationController.game = game;
    }
}
