package by.siarhei.kb2.app;

import by.siarhei.kb2.app.models.Game;

public interface Storage {
    boolean saveGame(Game game, String key);

    Game loadGame(String key);
}
