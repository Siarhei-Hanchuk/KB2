package by.siarhei.kb2.app.server.models.battle;

import java.util.Iterator;

public class Battle {
    private final BattleField battleField;
    private final Interactor ai;
    private final Interactor player;

    public Battle(BattleField battleField) {
        this.battleField = battleField;

        this.ai = new BattleAi(battleField);
        this.player = new PlayerControl(battleField);
    }

    public void interact(int x, int y) {
        if (battleField.isAiTurn()) {
            aiAction(x, y);
        } else {
            playerAction(x, y);
        }
        cleanUpDied();
    }

    private void aiAction(int x, int y) {
        ai.action(x, y);
        if(ai.finished()) {
            battleField.setAiTurn(false);
            resetEntities();

        }
    }

    private void playerAction(int x, int y) {
        player.action(x, y);
        if(player.finished()) {
            battleField.setAiTurn(true);
            resetEntities();
        }
    }


    // TODO: move deeper
    private void resetEntities() {
        Iterator<MapPointBattle> mapPoints = battleField.getMapPointsList();
        while(mapPoints.hasNext()) {
            MapPointBattle mapPoint = mapPoints.next();
            if (mapPoint.isEntity()) {
                mapPoint.getEntity().resetStep();
            }
        }
    }

    private void cleanUpDied() {
        Iterator<MapPointBattle> mapPoints = battleField.getMapPointsList();
        while(mapPoints.hasNext()) {
            MapPointBattle mapPoint = mapPoints.next();
            if (mapPoint.isEntity()) {
                WarriorEntity entity = mapPoint.getEntity();
                if(entity.getCount() <= 0) {
                    mapPoint.setEntity(null);
                }
            }
        }
    }
}
