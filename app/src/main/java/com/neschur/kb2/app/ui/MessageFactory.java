package com.neschur.kb2.app.ui;

import android.app.Activity;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.controllers.MainController;
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

public class MessageFactory {
    private static Activity activity;
    private static MainController mainController;

    public static void create(Activity activity, MainController mainController) {
        MessageFactory.activity = activity;
        MessageFactory.mainController = mainController;
    }

    public static Message getMessage(Entity entity) {
        if (entity instanceof MapNext)
            return new NextMapMessage(entity, activity.getResources(), getGameController());
        if (entity instanceof HarmfulMap)
            return new HarmfulMapMessage(entity, activity.getResources(), getGameController());
        if (entity instanceof GuidePost)
            return new GuidePostMessage(entity, activity.getResources(), getGameController());
        if (entity instanceof GoldChest && ((GoldChest) entity).isBonus())
            return new GoldChestMessage(entity, activity.getResources(), getGameController());
        if (entity instanceof Sorcerer)
            return new SorcererMessage(entity, activity.getResources(), getGameController());
        return null;
    }

    private static GameController getGameController() {
        return mainController.getGameController();
    }
}
