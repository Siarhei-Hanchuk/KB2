package by.siarhei.kb2.app.ui.messages;

import java.util.Random;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.Game;
import by.siarhei.kb2.app.server.entities.Entity;

public class CountySelectorUnavailableMessage extends Message {
    private static final int COUNT = 13;

    CountySelectorUnavailableMessage(Game game, I18n i18n) {
        super(null, game, i18n);
    }

    @Override
    public String getText() {
        return i18n.translate("county_selector_unavailable_message");
    }

    @Override
    public void action() {

    }
}
