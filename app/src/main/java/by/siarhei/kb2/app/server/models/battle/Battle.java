package by.siarhei.kb2.app.server.models.battle;

import java.util.Iterator;

public class Battle implements BattleParticipant {
    private final BattleField battleField;
    private final BattleParticipant ai;
    private final BattleParticipant player;

    Battle(BattleField battleField) {
        this.battleField = battleField;

        this.ai = new BattleAi(battleField);
        this.player = new PlayerControl(battleField);
    }

    @Override
    public void action(int x, int y) {
        if (battleField.isAiTurn()) {
            aiAction(x, y);
        } else {
            playerAction(x, y);
        }
        cleanUpDied();
    }

    @Override
    public boolean finished() {
        return battleField.finished();
    }

    public BattleResult getBattleResult() {
        return new BattleResult(battleField);
    }

    public BattleField getBattleField() {
        return battleField;
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
        Iterator<MapPoint> mapPoints = battleField.getMapPointsList();
        while(mapPoints.hasNext()) {
            MapPoint mapPoint = mapPoints.next();
            if (mapPoint.isEntity()) {
                mapPoint.getEntity().resetStep();
            }
        }
    }

    private void cleanUpDied() {
        Iterator<MapPoint> mapPoints = battleField.getMapPointsList();
        while(mapPoints.hasNext()) {
            MapPoint mapPoint = mapPoints.next();
            if (mapPoint.isEntity()) {
                Entity entity = mapPoint.getEntity();
                if(entity.getCount() <= 0) {
                    mapPoint.setEntity(null);
                }
            }
        }
    }
}
