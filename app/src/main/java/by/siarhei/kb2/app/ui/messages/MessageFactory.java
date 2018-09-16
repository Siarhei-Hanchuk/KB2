package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.controllers.GameOwner;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.entities.GoldChest;
import by.siarhei.kb2.app.server.entities.GuidePost;
import by.siarhei.kb2.app.server.entities.MapNext;
import by.siarhei.kb2.app.server.entities.Sorcerer;
import by.siarhei.kb2.app.server.entities.Spell;

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
