package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Game;

import java.util.Random;

public class SorcererMessage extends Message {
    private int mode;
    SorcererMessage(Entity entity, Game game, I18n i18n) {
        super(entity, game, i18n);
    }

    @Override
    public String getText() {
        mode = (new Random()).nextInt(12) + 1;
        return i18n.translate("entity_sorcerer_message" + mode);
    }

    @Override
    public void action() {
        switch (mode) {

        }
    }
}
