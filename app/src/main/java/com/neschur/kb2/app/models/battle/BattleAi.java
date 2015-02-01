package com.neschur.kb2.app.models.battle;

import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.Mover;

public class BattleAi {
    private BattleField bf;
    private Mover mover;

    public BattleAi(BattleField bf) {
        this.bf = bf;
        this.mover = new Mover(bf);
    }

    public void move() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (bf.isEntity(x, y) && !bf.isFriendly(x, y))
                    step(bf.getEntity(x, y));
            }
        }
    }

    private void step(WarriorEntity war) {
        if (war.isShoot() || war.isFly()) {
            MapPoint attackedPoint = findUserWar(true);
            if (attackedPoint == null)
                attackedPoint = findUserWar(false);
            if (attackedPoint != null)
                if (war.isShoot()) {
                    war.attack((WarriorEntity) attackedPoint.getEntity());
                } else {
                    if (teleportWarTo(war, attackedPoint.getX(), attackedPoint.getY()))
                        war.attack((WarriorEntity) attackedPoint.getEntity());
                }
        } else {
            MapPoint attackedPoint = findNearestUserWar(war);
            if (attackedPoint != null) {
                while (war.getStep() > 0) {
                    moveWarTo(war, attackedPoint);
                    if (distance(war, attackedPoint) == 1 && war.getStep() > 0) {
                        war.attack((WarriorEntity) attackedPoint.getEntity());
                    }
                }
            }
        }
    }

    private void moveWarTo(WarriorEntity war, MapPoint to) {
        mover.moveTo(war, to);
        war.reduceStep(1);
    }

    private boolean teleportWarTo(WarriorEntity war, int x, int y) {
        for (int i = (x - 1 < 0) ? 0 : x - 1; i <= ((x + 1 < 6) ? x + 1 : 6); i++) {
            for (int j = (y - 1 < 0) ? 0 : y - 1; j <= ((y + 1 < 5) ? y + 1 : 5); j++) {
                if (mover.teleport(war, bf.getMapPoint(i, j))) {
                    return true;
                }
            }
        }
        return false;
    }

    private MapPoint findUserWar(boolean shoot) {
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

    private MapPoint findNearestUserWar(WarriorEntity war) {
        int distance = 99;
        MapPoint dst = null;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (bf.isEntity(i, j) && bf.isFriendly(i, j)) {
                    int d = distance(war, bf.getMapPoint(i, j));
                    if (d < distance) {
                        distance = d;
                        dst = bf.getMapPoint(i, j);
                    }
                }
            }
        }
        return dst;
    }

    private int distance(WarriorEntity selected, MapPoint point) {
        return Math.max(Math.abs(selected.getMapPoint().getX() - point.getX()),
                Math.abs(selected.getMapPoint().getY() - point.getY()));
    }
}
