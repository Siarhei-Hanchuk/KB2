package com.neschur.kb2.app.ui.messages;

import android.content.res.Resources;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

import java.util.Random;

/**
 * Created by siarhei on 17.1.15.
 */
public class GoodMapMessage extends MapMessage {
    public GoodMapMessage(Entity entity, Resources resources, GameController gameController) {
        super(entity, resources, gameController);
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
