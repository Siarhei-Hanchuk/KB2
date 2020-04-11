package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.models.MapPoint;

public class NextMapMessage extends Message {
    NextMapMessage(MapPoint mapPoint, Game game, I18n i18n) {
        super(mapPoint, game, i18n);
    }

    @Override
    public void action() {
        player.upAvailableCountry();
        mapPoint.setEntity(null);
    }

    @Override
    public String getText() {
        return i18n.translate(R.string.entity_mapNext_message);
    }
}
