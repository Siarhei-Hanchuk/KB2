package com.neschur.kb2.app.ui;

import android.app.Activity;
import android.content.res.Resources;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.models.Player;

public abstract class Menu {
    protected Activity activity;
    protected GameController gameController;
    protected Resources resources;
    protected Player player;

    public Menu(Activity activity, GameController gameController){
        this.activity = activity;
        this.gameController = gameController;
        this.resources = activity.getResources();
        this.player = gameController.getPlayer();
    }

    public abstract int getCount();
    public abstract String getItemDescription(int i);
    public abstract boolean select(int i);
}
