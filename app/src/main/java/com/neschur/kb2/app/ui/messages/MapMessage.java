package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

import java.util.Random;

abstract class MapMessage extends Message {
    final int mode = Math.abs((new Random()).nextInt()) % getCount();

    MapMessage(Entity entity, GameController gameController) {
        super(entity, gameController);
    }

    abstract int getCount();
}
