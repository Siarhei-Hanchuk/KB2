package by.siarhei.kb2.app.server.models.battle;

import java.util.Iterator;

import by.siarhei.kb2.app.server.models.MapPoint;

public class BattleAi implements Interactor {
    private final BattleField battleField;

    public BattleAi(BattleField battleField ) {
        this.battleField = battleField ;
    }

    @Override
    public boolean finished() {
        Iterator<MapPointBattle> mapPoints = battleField.getMapPointsList();

        while(mapPoints.hasNext()) {
            MapPointBattle mapPoint = mapPoints.next();
            if (mapPoint.isEntity() && !mapPoint.isPlayerEntity()) {
                if (mapPoint.getEntity().getStep() > 0) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void action(int _x, int _y) {
        findWarrior();
    }

    private void findWarrior() {
        Iterator<MapPointBattle> mapPoints = battleField.getMapPointsList();

        while(mapPoints.hasNext()) {
            MapPointBattle point = mapPoints.next();
            if(!point.isEntity() || point.isPlayerEntity()) {
                continue;
            }
            WarriorEntity entity = point.getEntity();
            if (entity.getStep() > 0) {
                actWithPoint(point);
                return;
            }
        }
    }

    private void actWithPoint(MapPointBattle from) {
        MapPointBattle attackedPoint = findNearestUserWar(from);
        EntityActor actor = new EntityActor(battleField, from, attackedPoint);
        actor.tryMoveTo();
    }

    private MapPointBattle findNearestUserWar(MapPoint from) {
        int distance = 99;
        MapPointBattle dst = null;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (battleField.isEntity(i, j) && battleField.isPlayerEntity(i, j)) {
                    int d = battleField.distance(from, battleField.getMapPoint(i, j));
                    if (d < distance) {
                        distance = d;
                        dst = battleField.getMapPoint(i, j);
                    }
                }
            }
        }
        return dst;
    }
}
