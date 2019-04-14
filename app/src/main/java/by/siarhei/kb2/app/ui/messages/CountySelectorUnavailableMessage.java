package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.models.Game;

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
