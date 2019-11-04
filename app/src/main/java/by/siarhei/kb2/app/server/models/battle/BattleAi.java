package by.siarhei.kb2.app.server.models.battle;

import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Mover;

public class BattleAi {
    private final BattleField bf;

    public BattleAi(BattleField bf) {
        this.bf = bf;
    }

    public boolean isFinished() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (bf.isEntity(x, y) && !bf.isFriendly(x, y) && bf.getEntity(x, y).getStep() > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void move() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if(!bf.isEntity(x, y) || bf.isFriendly(x, y)) {
                    continue;
                }
                WarriorEntity entity = bf.getEntity(x, y);
                System.out.print(entity.getStep());
                System.out.print(" ");
                System.out.println(entity.getTextId());
                if (entity.getStep() > 0) {
                    bf.setSelected(bf.getEntity(x, y));
                    MapPoint mapPoint = bf.getMapPoint(x, y);
//                    System.out.println(((WarriorEntity)bf.getEntity(x, y)).getTextId());
                    step(mapPoint, bf.getEntity(x, y));
                    return;
                }
            }
        }
    }

//    TODO - check
    private void step(MapPoint from, WarriorEntity war) {
        MapPointBattle attackedPoint = findUserWar(true);
        if (attackedPoint == null)
            attackedPoint = findNearestUserWar(from);
        if (war.isShoot()) {
            war.attack(attackedPoint.getEntity());
        } else if (war.isFly()) {
            if (distance(from, attackedPoint) == 1)
                war.attack(attackedPoint.getEntity());
            else
                war.flyTo(attackedPoint);
        } else {
            if (distance(from, attackedPoint) == 1 && war.getStep() > 0) {
                war.attack(attackedPoint.getEntity());
            } else {
                Mover mover = new Mover(this.bf);
                mover.moveInDirection(war, from, attackedPoint);
                war.step--;
            }
        }
    }

    private MapPointBattle findUserWar(boolean shoot) {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (bf.isEntity(x, y) && bf.isFriendly(x, y)) {
                    if (shoot) {
                        if (bf.getEntity(x, y).isShoot())
                            return bf.getMapPoint(x, y);
                    } else {
                        return bf.getMapPoint(x, y);
                    }
                }
            }
        }
        return null;
    }

    private MapPointBattle findNearestUserWar(MapPoint from) {
        int distance = 99;
        MapPointBattle dst = null;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (bf.isEntity(i, j) && bf.isFriendly(i, j)) {
                    int d = distance(from, bf.getMapPoint(i, j));
                    if (d < distance) {
                        distance = d;
                        dst = bf.getMapPoint(i, j);
                    }
                }
            }
        }
        return dst;
    }

    private int distance(MapPoint from, MapPoint point) {
        return Math.max(Math.abs(from.getX() - point.getX()),
                Math.abs(from.getY() - point.getY()));
    }
}
