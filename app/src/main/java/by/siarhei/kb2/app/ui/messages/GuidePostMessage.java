package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.entities.Entity;
import by.siarhei.kb2.app.models.Game;

import java.util.Random;

public class GuidePostMessage extends Message {
    private static final int COUNT = 13;

    GuidePostMessage(Entity entity, Game game, I18n i18n) {
        super(entity, game, i18n);
    }

    @Override
    public String getText() {
        return i18n.translate("entity_guidePost_message" + ((new Random()).nextInt(COUNT) + 1));
    }

    @Override
    public void action() {

    }
}
