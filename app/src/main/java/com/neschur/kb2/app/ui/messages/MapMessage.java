package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Game;

import java.util.Random;

abstract class MapMessage extends Message {
    final int mode = Math.abs((new Random()).nextInt()) % getCount();

    MapMessage(Entity entity, Game game) {
        super(entity, game);
    }

    abstract int getCount();
}
