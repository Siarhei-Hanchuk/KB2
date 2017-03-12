package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.I18n;
import com.neschur.kb2.app.R;
import by.siarhei.kb2.app.entities.Entity;
import by.siarhei.kb2.app.models.Game;

public class NextMapMessage extends Message {
    NextMapMessage(Entity entity, Game game, I18n i18n) {
        super(entity, game, i18n);
    }

    @Override
    public void action() {
        player.upAvailableCountry();
        entity.destroy();
    }

    @Override
    public String getText() {
        return i18n.translate(R.string.entity_mapNext_message);
    }
}
