package com.neschur.kb2.app.ui.messages;

import android.content.res.Resources;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

import java.util.Random;

public abstract class MapMessage extends Message {
    protected int mode;

    public MapMessage(Entity entity, GameController gameController) {
        super(entity, gameController);
        mode = Math.abs((new Random()).nextInt()) % getCount();
    }

    protected abstract int getCount();
}
