package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Game;

import java.util.Random;

public class GuidePostMessage extends Message {
    private static final int COUNT = 12;

    GuidePostMessage(Entity entity, Game game) {
        super(entity, game);
    }

    @Override
    public String getText() {
        return I18n.translate("entity_guidePost_message" + (new Random()).nextInt(COUNT));
    }

    @Override
    public void action() {

    }
}
