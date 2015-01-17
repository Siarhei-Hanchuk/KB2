package com.neschur.kb2.app.ui.messages;

import android.content.res.Resources;

import com.neschur.kb2.app.R;
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
        switch (mode) {
            case 0:
                return resources.getString(R.string.entity_goodMap_message1);
            case 1:
                return resources.getString(R.string.entity_goodMap_message2);
        }
        return null;
    }

    public void action() {
        switch (mode) {
            case 0:
                player.changeAuthority(+player.getAuthority());
                return;
            case 1:
                // TODO
                return;
        }
        entity.destroy();
    }
}
