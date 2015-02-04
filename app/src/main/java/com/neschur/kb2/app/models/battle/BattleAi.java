package com.neschur.kb2.app.models.battle;

import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.Mover;

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
                if (bf.isEntity(x, y) && !bf.isFriendly(x, y) && bf.getEntity(x, y).getStep() > 0) {
                    bf.setSelected(bf.getEntity(x, y));
                    step(bf.getEntity(x, y));
                    return;
                }
            }
        }
    }

    private void step(WarriorEntity war) {
        MapPointBattle attackedPoint = findUserWar(true);
        if (attackedPoint == null)
            attackedPoint = findNearestUserWar(war);
        if (war.isShoot()) {
            war.attack(attackedPoint.getEntity());
        } else if (war.isFly()) {
            if (distance(war, attackedPoint) == 1)
                war.attack(attackedPoint.getEntity());
            else
                war.flyTo(attackedPoint);
        } else {
            if (distance(war, attackedPoint) == 1 && war.getStep() > 0) {
                war.attack(attackedPoint.getEntity());
            }
            war.moveInDirection(attackedPoint);
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

    private MapPointBattle findNearestUserWar(WarriorEntity war) {
        int distance = 99;
        MapPointBattle dst = null;
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
