package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Game;

public class SorcererMessage extends Message {

    SorcererMessage(Entity entity, Game game) {
        super(entity, game);
    }

    @Override
    public String getText() { // TODO
        return "";
    }

    @Override
    public void action() {

    }
}
