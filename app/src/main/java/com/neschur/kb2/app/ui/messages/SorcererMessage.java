package com.neschur.kb2.app.ui.messages;

import android.content.res.Resources;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

/**
 * Created by siarhei on 18.1.15.
 */
public class SorcererMessage extends Message {

    public SorcererMessage(Entity entity, Resources resources, GameController gameController) {
        super(entity, resources, gameController);
    }

    @Override
    public String getText() {
        return "IAAAAAAAAAAaaaaaaaaaaaaaaa";
    }

    @Override
    public void action() {

    }
}
