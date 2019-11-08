package by.siarhei.kb2.app.server.models.battle;

import java.util.Iterator;

import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.entities.Fighting;
import by.siarhei.kb2.app.ui.messages.Message;

public class BattleResult {
    private final BattleField battleField;
    private final Fighting fighting;

    public BattleResult(BattleField battleField) {
        this.battleField = battleField;
        this.fighting = battleField.getFighting();
    }

    public int getAuthority() {
        return win() * fighting.getAuthority() / 10;
    }

    public int getGold() {
        return win() * fighting.getAuthority() / 10;
    }

    public Message getMessage() {
        return Server.getMessageFactory().getBattleMessage(isWinner(), getAuthority(), getGold());
    }

    public boolean isWinner() {
        Iterator<MapPoint> iterator = battleField.getMapPointsList();
        while (iterator.hasNext()) {
            MapPoint point = iterator.next();

            if(point.isEntity() && !point.getEntity().isPlayerEntity()) {
                return false;
            }
        }

        return true;
    }

    private int win() {
        if(isWinner()) {
            return 1;
        } else {
            return -1;
        }
    }
}
