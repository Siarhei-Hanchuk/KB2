package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.entities.Entity;
import by.siarhei.kb2.app.models.Game;
import by.siarhei.kb2.app.models.Player;

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
