package by.siarhei.kb2.app.server.models.battle;

import java.util.Iterator;

import by.siarhei.kb2.app.server.models.Mover;
import by.siarhei.kb2.app.server.models.battle.interactors.AiInteractorImpl;
import by.siarhei.kb2.app.server.models.battle.interactors.PlayerInteractorImpl;

public class Battle {
    private final BattleField battleField;
    private final Mover mover;
    private final MoveArea moveArea;
    private final BattleAi ai;
    private final Interactor aiInteractor;
    private final Interactor playerInteractor;

    private boolean aiPhase = false;

    public Battle(BattleField battleField) {
        this.battleField = battleField;
        this.mover = new Mover(battleField);
        this.moveArea = new MoveArea(battleField);
        this.ai = new BattleAi(battleField);

        this.aiInteractor = new AiInteractorImpl(battleField);
        this.playerInteractor = new PlayerInteractorImpl(battleField);
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
        if(ai.isFinished()) {
            finishAiTurn();
        }
    }

    private void finishAiTurn() {
        this.aiPhase = false;
        finishPhase();
    }

    private void playerAction(int x, int y) {
        if(battleField.getSelectedPoint() == null) {
            selectOf(x, y);
        } else {
            tryMoveTo(x, y);
            tryFinishPhase();
        }
    }

    private void selectOf(int x, int y) {
        battleField.selectEntity(x, y);
        moveArea.build(battleField.getSelectedPoint());
    }

    private void tryMoveTo(int x, int y) {
        MapPointBattle targetPoint = battleField.getMapPoint(x, y);
        if (!targetPoint.isMovable()) {
            return;
        }

        moveTo(targetPoint);
    }

    private void moveTo(MapPointBattle targetPoint) {
        MapPointBattle selectedPoint = battleField.getSelectedPoint();
        WarriorEntity entity = targetPoint.getEntity();
        if (selectedPoint.isLand() && entity  == null) {
            entity.reduceStep(battleField.distance(selectedPoint, targetPoint));
            mover.teleport(entity, selectedPoint, targetPoint);

            if (entity.getStep() > 0) {
                moveArea.build(selectedPoint);
            } else {
                moveArea.clearMoveArea();
                finishWithSelected();
            }
        } else {
            attack(targetPoint, moveArea);
        }
    }

    private void attack(MapPointBattle toPoint, MoveArea moveArea) {
        MapPointBattle fromPoint = battleField.getSelectedPoint();
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

    private void tryFinishPhase() {
        boolean finish = true;
        Iterator<MapPointBattle> mapPoints = battleField.getMapPointsList();

        while(mapPoints.hasNext()) {
            MapPointBattle mapPoint = mapPoints.next();
            if (mapPoint.isEntity() && mapPoint.isPlayerEntity()) {
                if (mapPoint.getEntity().getStep() > 0)
                    finish = false;
            }
        }

        if(finish) {
            finishPhase();
            this.aiPhase = true;
        }
    }

    private void finishPhase() {
        Iterator<MapPointBattle> mapPoints = battleField.getMapPointsList();
        while(mapPoints.hasNext()) {
            MapPointBattle mapPoint = mapPoints.next();
            if (mapPoint.isEntity()) {
                mapPoint.getEntity().resetStep();
            }
        }
    }

    private void attackEntity(WarriorEntity fromEntity, WarriorEntity toEntity) {

    }
}
