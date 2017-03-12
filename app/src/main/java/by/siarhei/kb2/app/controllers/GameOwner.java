package by.siarhei.kb2.app.controllers;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.models.Game;

public interface GameOwner {
    Game getGame();

    I18n i18n();
}
