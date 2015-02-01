package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Game;

public class NextMapMessage extends Message {
    NextMapMessage(Entity entity, Game game) {
        super(entity, game);
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
