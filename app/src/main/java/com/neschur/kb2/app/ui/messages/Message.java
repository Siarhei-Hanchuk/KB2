package com.neschur.kb2.app.ui.messages;

import android.content.res.Resources;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Player;

/**
 * Created by siarhei on 17.1.15.
 */
public abstract class Message {
    protected Resources resources;
    protected Player player;
    protected Entity entity;
    protected GameController gameController;

    public Message(Entity entity, Resources resources, GameController gameController) {
        this.player = gameController.getPlayer();
        this.gameController = gameController;
        this.resources = resources;
        this.entity = entity;
    }

    public abstract String getText();
    public abstract void action();
}
