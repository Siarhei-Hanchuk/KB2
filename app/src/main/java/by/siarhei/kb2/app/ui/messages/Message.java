package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Player;

public abstract class Message {
    final Player player;
    final MapPoint mapPoint;
    final Game game;
    final I18n i18n;

    Message(MapPoint mapPoint, Game game, I18n i18n) {
        this.i18n = i18n;
        this.player = game.getPlayer();
        this.game = game;
        this.mapPoint = mapPoint;
    }

    public abstract String getText();

    public abstract void action();
}
