package com.neschur.kb2.app.ui;

import android.app.Activity;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.GuidePost;
import com.neschur.kb2.app.entities.HarmfulMap;
import com.neschur.kb2.app.entities.MapNext;
import com.neschur.kb2.app.entities.Sorcerer;
import com.neschur.kb2.app.ui.messages.GoldChestMessage;
import com.neschur.kb2.app.ui.messages.GuidePostMessage;
import com.neschur.kb2.app.ui.messages.HarmfulMapMessage;
import com.neschur.kb2.app.ui.messages.Message;
import com.neschur.kb2.app.ui.messages.NextMapMessage;
import com.neschur.kb2.app.ui.messages.SorcererMessage;

/**
 * Created by siarhei on 14.1.15.
 */
public class MessageFactory {
    private static Activity activity;
    private static GameController gameController;

    public static void create(Activity activity, GameController gameController) {
        MessageFactory.activity = activity;
        MessageFactory.gameController = gameController;
    }

    public static Message getMessage(Entity entity) {
        if (entity instanceof MapNext)
            return new NextMapMessage(entity, activity.getResources(), gameController);
        if (entity instanceof HarmfulMap)
            return new HarmfulMapMessage(entity, activity.getResources(), gameController);
        if (entity instanceof GuidePost)
            return new GuidePostMessage(entity, activity.getResources(), gameController);
        if (entity instanceof GoldChest && ((GoldChest) entity).isBonus())
            return new GoldChestMessage(entity, activity.getResources(), gameController);
        if (entity instanceof Sorcerer)
            return new SorcererMessage(entity, activity.getResources(), gameController);
        return null;
    }
}
