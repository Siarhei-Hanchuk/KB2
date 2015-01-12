package com.neschur.kb2.app.ui;

import android.app.Activity;

import com.neschur.kb2.app.controllers.GameController;

public abstract class Menu {
    protected Activity activity;
    protected GameController gameController;

    public Menu(Activity activity, GameController gameController){
        this.activity = activity;
        this.gameController = gameController;
    }

    public abstract int getCount();
    public abstract String getItemDescription(int i);
    public abstract boolean select(int i);
}
