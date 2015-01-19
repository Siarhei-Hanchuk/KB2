package com.neschur.kb2.app.ui.messages;

import android.content.res.Resources;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

public class SorcererMessage extends Message {

    public SorcererMessage(Entity entity, Resources resources, GameController gameController) {
        super(entity, resources, gameController);
    }

    @Override
    public String getText() { // TODO
        return "";
    }

    @Override
    public void action() {

    }
}
