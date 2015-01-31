package com.neschur.kb2.app.models;

import com.neschur.kb2.app.Mover;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.WarriorEntity;

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
            WarriorEntity attacked = findUserWar(true);
            if (attacked == null)
                attacked = findUserWar(false);
            if (attacked != null)
                if (war.isShoot()) {
                    war.attack(attacked);
                } else {
                    if (teleportWarTo(war, attacked.getX(), attacked.getY()))
                        war.attack(attacked);
                }
        } else {
            WarriorEntity attacked = findNearestUserWar(war);
            if (attacked != null) {
                while (war.getStep() > 0) {
                    moveWarTo(war, attacked.getMapPoint());
                    if (distance(war, attacked.getX(), attacked.getY()) == 1 && war.getStep() > 0) {
                        war.attack(attacked);
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
                mover.teleport(war, bf.getMapPoint(i,j));
            }
        }
        return false;
    }

    private WarriorEntity findUserWar(boolean shoot) {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (bf.isEntity(x, y) && bf.isFriendly(x, y)) {
                    if (shoot) {
                        if (bf.getEntity(x, y).isShoot())
                            return bf.getEntity(x, y);
                    } else {
                        return bf.getEntity(x, y);
                    }
                }
            }
        }
        return null;
    }

    private WarriorEntity findNearestUserWar(WarriorEntity war) {
        int distance = 99;
        WarriorEntity dst = null;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if(bf.isEntity(i, j) && bf.isFriendly(i, j)) {
                    int d = distance(war, i, j);
                    if (d < distance) {
                        distance = d;
                        dst = bf.getEntity(i, j);
                    }
                }
            }
        }
        return dst;
    }

    private int distance(WarriorEntity selected, int x, int y) {
        return Math.max(Math.abs(selected.getX() - x), Math.abs(selected.getY() - y));
    }
}
