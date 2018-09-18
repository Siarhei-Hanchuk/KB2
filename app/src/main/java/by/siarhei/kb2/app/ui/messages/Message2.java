package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.Game;
import by.siarhei.kb2.app.server.models.Player;

public abstract class Message2 {
    final Player player;
    final Entity entity;
    final Game game;
    final I18n i18n;

    Message2(Entity entity, Game game, I18n i18n) {
        this.i18n = i18n;
        this.player = game.getPlayer();
        this.game = game;
        this.entity = entity;
    }

    public abstract String getTextId();

    public abstract void action();
}
