package com.neschur.kb2.app.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.SurfaceView;

import com.neschur.kb2.app.models.Game;

public abstract class ApplicationController implements ViewController, GameOwner {
    private static Game game;
    protected final Activity activity;

    protected ApplicationController(Activity activity) {
        this.activity = activity;
    }

    protected void setContentView(SurfaceView view) {
        activity.setContentView(view);
    }

    @Override
    public Context getContext() {
        return activity;
    }

    @Override
    public Game getGame() {
        return game;
    }

    protected void setGame(Game game) {
        ApplicationController.game = game;
    }
}
