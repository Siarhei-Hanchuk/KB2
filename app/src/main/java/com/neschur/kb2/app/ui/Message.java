package com.neschur.kb2.app.ui;

import android.content.res.Resources;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.models.Player;

/**
 * Created by siarhei on 17.1.15.
 */
public abstract class Message {
    protected Resources resources;
    protected Player player;

    public Message(Resources resources, GameController gameController) {
        this.player = gameController.getPlayer();
        this.resources = resources;
    }

    public abstract String getText();
    public abstract void action();
}
