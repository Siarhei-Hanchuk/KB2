package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.Game;

public class GuidePostMessage2 extends Message2 {
    private static final int COUNT = 13;

    GuidePostMessage2(Entity entity, Game game) {
        super(entity, game, null);
    }

    @Override
    public String getTextId() {
        return "entity_guidePost_message";
    }

    @Override
    public void action() {

    }
}
