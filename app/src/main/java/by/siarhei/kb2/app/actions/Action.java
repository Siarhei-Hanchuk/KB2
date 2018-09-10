package by.siarhei.kb2.app.actions;

import by.siarhei.kb2.app.entities.GoldChest;
import by.siarhei.kb2.app.models.Game;

interface Action {
    public void call(Game game, GoldChest chest);
}
