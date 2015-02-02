package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Game;

import java.util.Random;

abstract class MapMessage extends Message {
    final int mode = Math.abs((new Random()).nextInt()) % getCount();

    MapMessage(Entity entity, Game game, I18n i18n) {
        super(entity, game, i18n);
    }

    abstract int getCount();
}
