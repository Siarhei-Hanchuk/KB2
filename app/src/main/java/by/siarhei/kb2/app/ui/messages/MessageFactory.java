package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.entities.GoldenChest;
import by.siarhei.kb2.app.server.entities.GuidePost;
import by.siarhei.kb2.app.server.entities.MapNext;
import by.siarhei.kb2.app.server.entities.Sorcerer;
import by.siarhei.kb2.app.server.entities.Spell;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.models.MapPoint;

public class MessageFactory {
    private final Game game;
    private final I18n i18n;

    public MessageFactory(Game game, I18n i18n) {
        this.game = game;
        this.i18n = i18n;
    }

    public Message getMessage(MapPoint mapPoint) {
        Entity entity = mapPoint.getEntity();
        if (entity instanceof MapNext)
            return new NextMapMessage(mapPoint, game, i18n);
        if (entity instanceof Spell)
            return new SpellMessage(mapPoint, game, i18n);
        if (entity instanceof GuidePost)
            return new GuidePostMessage(mapPoint, game, i18n);
        if (entity instanceof GoldenChest && ((GoldenChest) entity).isBonus())
            return new GoldChestMessage(mapPoint, game, i18n);
        if (entity instanceof Sorcerer)
            return new SorcererMessage(mapPoint, game, i18n);
        return null;
    }

    public Message getBattleMessage(boolean result, int authority, int money) {
        return new BattleFinishMessage(game, i18n,
                result, authority, money);
    }

    public Message getCountySelectorUnavailableMessage() {
        return new CountySelectorUnavailableMessage(game, i18n);
    }
}
