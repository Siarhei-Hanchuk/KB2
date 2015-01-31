package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Player;

public abstract class Message {
    final Player player;
    final Entity entity;
    final GameController gameController;

    Message(Entity entity, GameController gameController) {
        this.player = gameController.getPlayer();
        this.gameController = gameController;
        this.entity = entity;
    }

    public abstract String getText();

    public abstract void action();
}
