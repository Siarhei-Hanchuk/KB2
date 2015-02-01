package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.controllers.MainController;
import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.GuidePost;
import com.neschur.kb2.app.entities.HarmfulMap;
import com.neschur.kb2.app.entities.MapNext;
import com.neschur.kb2.app.entities.Sorcerer;

public class MessageFactory {
    private final ViewController mainController;

    public MessageFactory (ViewController mainController) {
        this.mainController = mainController;
    }

    public Message getMessage(Entity entity) {
        if (entity instanceof MapNext)
            return new NextMapMessage(entity, getGameController());
        if (entity instanceof HarmfulMap)
            return new HarmfulMapMessage(entity, getGameController());
        if (entity instanceof GuidePost)
            return new GuidePostMessage(entity, getGameController());
        if (entity instanceof GoldChest && ((GoldChest) entity).isBonus())
            return new GoldChestMessage(entity, getGameController());
        if (entity instanceof Sorcerer)
            return new SorcererMessage(entity, getGameController());
        return null;
    }

    private GameController getGameController() {
        return mainController.getGameController();
    }
}
