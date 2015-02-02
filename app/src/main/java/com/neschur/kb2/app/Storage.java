package com.neschur.kb2.app;

import com.neschur.kb2.app.models.Game;

public interface Storage {
    public boolean saveGame(Game game, String key);

    public Game loadGame(String key);
}
