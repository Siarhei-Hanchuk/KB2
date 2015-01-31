package com.neschur.kb2.app.ui.messages;

import android.content.res.Resources;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

public class NextMapMessage extends Message {
    public NextMapMessage(Entity entity, GameController gameController) {
        super(entity, gameController);
    }

    @Override
    public void action() {
        player.upAvailableCountry();
        entity.destroy();
    }

    @Override
    public String getText() {
        return I18n.translate(R.string.entity_mapNext_message);
    }
}
