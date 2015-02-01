package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Game;
import com.neschur.kb2.app.models.Player;

public abstract class Message {
    final Player player;
    final Entity entity;
    final Game game;

    Message(Entity entity, Game game) {
        this.player = game.getPlayer();
        this.game = game;
        this.entity = entity;
    }

    public abstract String getText();

    public abstract void action();
}
