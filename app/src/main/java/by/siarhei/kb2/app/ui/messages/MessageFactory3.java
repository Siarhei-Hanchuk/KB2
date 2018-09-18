package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.entities.GoldChest;
import by.siarhei.kb2.app.server.entities.GuidePost;
import by.siarhei.kb2.app.server.entities.MapNext;
import by.siarhei.kb2.app.server.entities.Sorcerer;
import by.siarhei.kb2.app.server.entities.Spell;
import by.siarhei.kb2.app.server.Game;

public class MessageFactory3 {
    private final Game game;
    private final I18n i18n;

    public MessageFactory3(Game game, I18n i18n) {
        this.game = game;
        this.i18n = i18n;
    }

    public Message getMessage(Entity entity) {
        if (entity instanceof MapNext)
            return new NextMapMessage(entity, game, i18n);
        if (entity instanceof Spell)
            return new SpellMessage(entity, game, i18n);
        if (entity instanceof GuidePost)
            return new GuidePostMessage(entity, game, i18n);
        if (entity instanceof GoldChest && ((GoldChest) entity).isBonus())
            return new GoldChestMessage(entity, game, i18n);
        if (entity instanceof Sorcerer)
            return new SorcererMessage(entity, game, i18n);
        return null;
    }

    public Message getBattleMessage(boolean result, int authority, int money) {
        return new BattleFinishMessage(game, i18n,
                result, authority, money);
    }
}