package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Game;

import java.util.Random;

public class GoodMapMessage extends MapMessage {
    GoodMapMessage(Entity entity, Game game, I18n i18n) {
        super(entity, game, i18n);
    }

    @Override
    protected int getCount() {
        return 2;
    }

    @Override
    public String getText() {
        return i18n.translate("entity_goodMap_message1" + (mode + 1));
    }

    @Override
    public void action() {
        switch (mode) {
            case 0:
                player.changeAuthority(+player.getAuthority());
                return;
            case 1:
                player.setImportantDocs((new Random()).nextInt() % 5);
                return;
            case 2:
                // TODO
                return;
            case 3:
                // TODO
                return;
            case 4:
                player.changeMoney(+player.getMoney());
                return;
            case 5:
                // TODO
                return;
        }
        entity.destroy();
    }
}
