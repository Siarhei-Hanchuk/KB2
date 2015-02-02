package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.models.Game;

public interface GameOwner {
    public Game getGame();

    public I18n i18n();
}
