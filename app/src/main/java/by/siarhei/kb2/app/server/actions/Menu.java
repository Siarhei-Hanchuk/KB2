package by.siarhei.kb2.app.server.actions;

import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.models.Player;

public abstract class Menu {
    final Game game;
    final Player player;

    Menu(Game game) {
        this.game = game;
        this.player = game.getPlayer();
    }

    public abstract boolean select(int i);
}
