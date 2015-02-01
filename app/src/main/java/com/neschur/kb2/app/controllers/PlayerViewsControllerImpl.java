package com.neschur.kb2.app.controllers;

import android.app.Activity;
import android.view.SurfaceView;

import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.views.ViewFactory;

public class PlayerViewsControllerImpl extends ApplicationController
        implements PlayerViewsController, MagicViewController {
    public PlayerViewsControllerImpl(Activity activity, String viewName) {
        super(activity);
        switch (viewName) {
            case "magic":
                setContentView(ViewFactory.getMagicView(this));
                break;
            case "army":
                setContentView(ViewFactory.getArmyView(this));
                break;
            case "map":
                setContentView(ViewFactory.getMapView(this));
                break;
        }
    }

    @Override
    public Player getPlayer() {
        return getGameController().getPlayer();
    }

    @Override
    public void viewClose() {
        new MainViewControllerImpl(activity);
    }

    @Override
    public void takeArmy(String id) {

    }
}
