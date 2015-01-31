package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

public class SorcererMessage extends Message {

    SorcererMessage(Entity entity, GameController gameController) {
        super(entity, gameController);
    }

    @Override
    public String getText() { // TODO
        return "";
    }

    @Override
    public void action() {

    }
}
