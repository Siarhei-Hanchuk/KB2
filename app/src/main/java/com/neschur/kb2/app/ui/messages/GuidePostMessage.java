package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Game;

import java.util.Random;

public class GuidePostMessage extends Message {
    private static final int COUNT = 13;

    GuidePostMessage(Entity entity, Game game, I18n i18n) {
        super(entity, game, i18n);
    }

    @Override
    public String getText() {
        return i18n.translate("entity_guidePost_message" + ((new Random()).nextInt(COUNT) + 1));
    }

    @Override
    public void action() {

    }
}
