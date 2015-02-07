package com.neschur.kb2.app.ui.messages;

import com.neschur.kb2.app.controllers.GameOwner;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.GuidePost;
import com.neschur.kb2.app.entities.MapNext;
import com.neschur.kb2.app.entities.Sorcerer;
import com.neschur.kb2.app.entities.Spell;

public class MessageFactory {
    private final GameOwner gameOwner;

    public MessageFactory(GameOwner gameOwner) {
        this.gameOwner = gameOwner;
    }

    public Message getMessage(Entity entity) {
        if (entity instanceof MapNext)
            return new NextMapMessage(entity, gameOwner.getGame(), gameOwner.i18n());
        if (entity instanceof Spell)
            return new SpellMessage(entity, gameOwner.getGame(), gameOwner.i18n());
        if (entity instanceof GuidePost)
            return new GuidePostMessage(entity, gameOwner.getGame(), gameOwner.i18n());
        if (entity instanceof GoldChest && ((GoldChest) entity).isBonus())
            return new GoldChestMessage(entity, gameOwner.getGame(), gameOwner.i18n());
        if (entity instanceof Sorcerer)
            return new SorcererMessage(entity, gameOwner.getGame(), gameOwner.i18n());
        return null;
    }

    public Message getBattleMessage(boolean result, int authority, int money) {
        return new BattleFinishMessage(gameOwner.getGame(), gameOwner.i18n(),
                result, authority, money);
    }
}
