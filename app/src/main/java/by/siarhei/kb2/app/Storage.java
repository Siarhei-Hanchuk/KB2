package by.siarhei.kb2.app;

import by.siarhei.kb2.app.server.models.Game;

public interface Storage {
    boolean saveGame(String data, String key);

    String loadGame(String key);
}
