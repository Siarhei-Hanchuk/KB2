package com.neschur.kb2.app.ui.messages;

import android.content.res.Resources;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

import java.util.Random;

/**
 * Created by siarhei on 17.1.15.
 */
public class GuidePostMessage extends Message {
    private static int COUNT = 12;

    public GuidePostMessage(Entity entity, Resources resources, GameController gameController) {
        super(entity, resources, gameController);
    }

    @Override
    public String getText() {
        return I18n.translate("entity_guidePost_message" + (new Random()).nextInt(COUNT));
    }

    @Override
    public void action() {

    }
}
