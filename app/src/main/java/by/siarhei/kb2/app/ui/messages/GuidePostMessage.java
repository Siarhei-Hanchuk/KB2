package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.models.MapPoint;

import java.util.Random;

public class GuidePostMessage extends Message {
    private static final int COUNT = 13;

    GuidePostMessage(MapPoint mapPoint, Game game, I18n i18n) {
        super(mapPoint, game, i18n);
    }

    @Override
    public String getText() {
        return i18n.translate("entity_guidePost_message" + ((new Random()).nextInt(COUNT) + 1));
    }

    @Override
    public void action() {

    }
}
