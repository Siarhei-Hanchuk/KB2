package by.siarhei.kb2.app.server.models.battle;

import java.util.Iterator;

public class BattleAi implements Participant {
    private final BattleField battleField;

    BattleAi(BattleField battleField ) {
        this.battleField = battleField ;
    }

    @Override
    public boolean finished() {
        Iterator<MapPoint> mapPoints = battleField.getMapPointsList();

        while(mapPoints.hasNext()) {
            MapPoint mapPoint = mapPoints.next();
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
        Iterator<MapPoint> mapPoints = battleField.getMapPointsList();

        while(mapPoints.hasNext()) {
            MapPoint point = mapPoints.next();
            if(!point.isEntity() || point.isPlayerEntity()) {
                continue;
            }
            Entity entity = point.getEntity();
            if (entity.getStep() > 0) {
                actWithPoint(point);
                return;
            }
        }
    }

    private void actWithPoint(MapPoint from) {
        MapPoint attackedPoint = findNearestUserWar(from);
        EntityActor actor;
        if(from.getEntity().isShoot()) {
            actor = new EntityActor(battleField, from, attackedPoint);
        } else {
            MapPoint movePoint = pointInDirection(from, attackedPoint);
            actor = new EntityActor(battleField, from, movePoint);
        }
        actor.tryMoveTo();
    }

    private MapPoint findNearestUserWar(MapPoint from) {
        int distance = 99;
        MapPoint dst = null;
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

    private MapPoint pointInDirection(MapPoint from, MapPoint to) {
        int x = from.getX();
        int y = from.getY();
        int directionX = (int) Math.signum(to.getX() - x);
        int directionY = (int) Math.signum(to.getY() - y);
        return battleField.getMapPoint(x + directionX, y + directionY);
    }
}
