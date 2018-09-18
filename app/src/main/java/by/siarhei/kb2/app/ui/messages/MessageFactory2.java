package by.siarhei.kb2.app.ui.messages;

import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.Game;

public class MessageFactory2 {
    private final Game game;

    public MessageFactory2(Game game) {
        this.game= game;
    }

    public Message getMessage(Entity entity) {
//        if (entity instanceof GuidePost)
//            return new GuidePostMessage2(entity, game);
        return null;
    }
}
