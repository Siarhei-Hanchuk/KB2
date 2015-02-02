package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Game;
import com.neschur.kb2.app.models.Player;

public abstract class Message {
    final Player player;
    final Entity entity;
    final Game game;
    final I18n i18n;

    Message(Entity entity, Game game, I18n i18n) {
        this.i18n = i18n;
        this.player = game.getPlayer();
        this.game = game;
        this.entity = entity;
    }

    public abstract String getText();

    public abstract void action();
}
