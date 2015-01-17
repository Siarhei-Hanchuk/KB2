package com.neschur.kb2.app.ui;

import android.content.res.Resources;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

/**
 * Created by siarhei on 17.1.15.
 */
public class MapNextMessage extends Message {
    public MapNextMessage(Entity entity, Resources resources, GameController gameController) {
        super(entity, resources, gameController);
    }

    @Override
    public void action() {
        player.upAvailableCountry();
        entity.destroy();
    }

    @Override
    public String getText() {
        return resources.getString(R.string.entity_mapNext_message);
    }
}
