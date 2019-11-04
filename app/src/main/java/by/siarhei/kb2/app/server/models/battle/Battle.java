package by.siarhei.kb2.app.server.models.battle;

import by.siarhei.kb2.app.server.models.Mover;

public class Battle {
    private final int XSize = 6;
    private final int YSize = 5;

    private BattleField battleField;
    private Mover mover;
    private MoveArea moveArea;
    private final BattleAi ai;

    private boolean aiPhase = false;

    public Battle(BattleField battleField) {
        this.battleField = battleField;
        this.mover = new Mover(battleField);
        this.moveArea = new MoveArea(battleField);
        this.ai = new BattleAi(battleField);
    }

    public void interact(int x, int y) {
        if (aiPhase) {
            aiControl();
        } else {
            playerAction(x, y);
        }
    }

    private void aiControl() {
        ai.move();
        this.aiPhase = !ai.isFinished();
        if(!this.aiPhase) {
            finishPhase();
        };
    }

    private void playerAction(int x, int y) {
        if(battleField.getSelected() != null) {
            moveTo(x, y);
        } else {
            battleField.selectEntity(x, y);
            moveArea.build(x, y, battleField.getSelected());
        }

        tryFinishPhase();
    }

    private void moveTo(int x, int y) {
        MapPointBattle targetPoint = battleField.getMapPoint(x, y);
        if (!targetPoint.isMove()) {
            return;
        }

        MapPointBattle selectedPoint = battleField.getSelected();
        WarriorEntity entity = selectedPoint.getEntity();
        if (selectedPoint.isLand() && entity  == null) {
            entity.reduceStep(battleField.distance(selectedPoint, targetPoint));
            mover.teleport(entity, selectedPoint, targetPoint);

            if (entity.getStep() > 0) {
                moveArea.build(x, y, selectedPoint);
            } else {
                moveArea.clearMoveArea();
                finishWithSelected();
            }
        } else {
            attack(x, y, moveArea);
        }
    }

    private void attack(int x, int y, MoveArea moveArea) {
        MapPointBattle toPoint = battleField.getMapPoint(x, y);
        MapPointBattle fromPoint = battleField.getSelected();
        if (toPoint.isEntity() && !toPoint.isPlayerEntity()) {
            if (battleField.distance(fromPoint, toPoint) == 1 || fromPoint.getEntity().isShoot()) {
                fromPoint.getEntity().attack(toPoint.getEntity());
                moveArea.clearMoveArea();
                finishWithSelected();
            }
        }
    }

    private void finishWithSelected() {
        battleField.setSelected(null);
    }

    private boolean tryFinishPhase() {
        boolean finish = true;
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
                MapPointBattle mapPoint = battleField.getMapPoint(x, y);
                if (mapPoint.isEntity() && mapPoint.isPlayerEntity()) {
                    if (mapPoint.getEntity().getStep() > 0)
                        finish = false;
                }
            }
        }
        if(finish) {
            finishPhase();
            this.aiPhase = true;
            return true;
        }
        return false;
    }

    private void finishPhase() {
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
                MapPointBattle mapPointBattle = battleField.getMapPoint(x, y);
                if (mapPointBattle.isEntity()) {
                    mapPointBattle.getEntity().resetStep();
                }
            }
        }
    }

}
