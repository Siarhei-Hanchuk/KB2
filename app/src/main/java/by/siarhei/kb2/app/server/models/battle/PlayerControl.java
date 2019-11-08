package by.siarhei.kb2.app.server.models.battle;

import java.util.Iterator;

class PlayerControl implements Participant {
    private final BattleField battleField;
    private final MoveArea moveArea;

    PlayerControl(BattleField battleField) {
        this.battleField = battleField;
        this.moveArea = new MoveArea(battleField);
    }

    @Override
    public void action(int x, int y) {
        if(battleField.getSelectedPoint() == null) {
            System.out.println("SELECT");
            selectOf(x, y);
        } else {
            System.out.println("tryMoveTo");
            tryMoveTo(x, y);
        }
    }

    @Override
    public boolean finished() {
        Iterator<MapPoint> mapPoints = battleField.getMapPointsList();

        while(mapPoints.hasNext()) {
            MapPoint mapPoint = mapPoints.next();
            if (mapPoint.isEntity() && mapPoint.isPlayerEntity()) {
                if (mapPoint.getEntity().getStep() > 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private void selectOf(int x, int y) {
        battleField.selectEntity(x, y);
        if(battleField.getSelectedPoint() != null) {
            moveArea.build(battleField.getSelectedPoint());
        }
    }

    private void tryMoveTo(int x, int y) {
        MapPoint to = battleField.getMapPoint(x, y);
        if (!to.isMovable()) return;

        MapPoint from = battleField.getSelectedPoint();
        Entity entity = from.getEntity();
        EntityActor actor = new EntityActor(battleField, from, to);
        actor.tryMoveTo();
        if(entity.getStep() <= 0) {
            moveArea.clearMoveArea();
            battleField.setSelected(null);
        } else {
            battleField.setSelected(entity);
            moveArea.build(battleField.getSelectedPoint());
        }
    }

}
