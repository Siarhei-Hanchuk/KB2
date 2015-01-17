package com.neschur.kb2.app.ui.messages;

import android.content.res.Resources;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

/**
 * Created by siarhei on 17.1.15.
 */
public class HarmfulMapMessage extends MapMessage {
    public HarmfulMapMessage(Entity entity, Resources resources, GameController gameController) {
        super(entity, resources, gameController);
    }

    @Override
    protected int getCount() {
        return 5;
    }

    @Override
    public String getText() {
        return I18n.translate("entity_harmfulMap_message" + (mode + 1));
    }

    public void action() {
        switch (mode) {
            case 0:
                for (int i = 0; i < 4; i++)
                    player.changeWorker(i, -player.getWorker(i));
                return;
            case 1:
                // TODO bad destroy
                if (gameController.getNave())
                    gameController.destroyNave();
                return;
            case 2:
                // TODO
                return;
            case 3:
                // TODO
                return;
            case 4:
                player.changeMoney(-player.getMoney());
                return;
        }
        entity.destroy();
    }
}
