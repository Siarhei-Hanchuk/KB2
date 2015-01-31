package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

import java.util.Random;

public class GoodMapMessage extends MapMessage {
    GoodMapMessage(Entity entity, GameController gameController) {
        super(entity, gameController);
    }

    protected int getCount() {
        return 2;
    }

    @Override
    public String getText() {
        return I18n.translate("entity_goodMap_message1" + (mode + 1));
    }

    public void action() {
        switch (mode) {
            case 0:
                player.changeAuthority(+player.getAuthority());
                return;
            case 1:
                player.setImportantDocs((new Random()).nextInt() % 5);
                return;
        }
        entity.destroy();
    }
}
