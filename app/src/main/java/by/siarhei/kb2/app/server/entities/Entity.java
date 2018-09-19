package by.siarhei.kb2.app.server.entities;

import by.siarhei.kb2.app.server.models.Player;

public interface Entity {
    int getID();

    default int getID(Player player) {
        return getID();
    }
}
