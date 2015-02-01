package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.controllers.GameOwner;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.GuidePost;
import com.neschur.kb2.app.entities.HarmfulMap;
import com.neschur.kb2.app.entities.MapNext;
import com.neschur.kb2.app.entities.Sorcerer;

public class MessageFactory {
    private final GameOwner controller;

    public MessageFactory(GameOwner mainController) {
        this.controller = mainController;
    }

    public Message getMessage(Entity entity) {
        if (entity instanceof MapNext)
            return new NextMapMessage(entity, controller.getGameController());
        if (entity instanceof HarmfulMap)
            return new HarmfulMapMessage(entity, controller.getGameController());
        if (entity instanceof GuidePost)
            return new GuidePostMessage(entity, controller.getGameController());
        if (entity instanceof GoldChest && ((GoldChest) entity).isBonus())
            return new GoldChestMessage(entity, controller.getGameController());
        if (entity instanceof Sorcerer)
            return new SorcererMessage(entity, controller.getGameController());
        return null;
    }

    public Message getBattleMessage(boolean result) {
        return new BattleFinishMessage(null, controller.getGameController(), result);
    }
}
