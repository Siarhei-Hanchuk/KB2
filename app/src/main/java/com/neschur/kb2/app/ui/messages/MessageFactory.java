package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.controllers.GameOwner;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.GuidePost;
import com.neschur.kb2.app.entities.HarmfulMap;
import com.neschur.kb2.app.entities.MapNext;
import com.neschur.kb2.app.entities.Sorcerer;

public class MessageFactory {
    private final GameOwner gameOwner;

    public MessageFactory(GameOwner gameOwner) {
        this.gameOwner = gameOwner;
    }

    public Message getMessage(Entity entity) {
        if (entity instanceof MapNext)
            return new NextMapMessage(entity, gameOwner.getGame());
        if (entity instanceof HarmfulMap)
            return new HarmfulMapMessage(entity, gameOwner.getGame());
        if (entity instanceof GuidePost)
            return new GuidePostMessage(entity, gameOwner.getGame());
        if (entity instanceof GoldChest && ((GoldChest) entity).isBonus())
            return new GoldChestMessage(entity, gameOwner.getGame());
        if (entity instanceof Sorcerer)
            return new SorcererMessage(entity, gameOwner.getGame());
        return null;
    }

    public Message getBattleMessage(boolean result) {
        return new BattleFinishMessage(gameOwner.getGame(), result);
    }
}
