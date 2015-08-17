package com.neschur.kb2.app;

import com.neschur.kb2.app.models.Game;

public interface Storage {
    boolean saveGame(Game game, String key);

    Game loadGame(String key);
}
