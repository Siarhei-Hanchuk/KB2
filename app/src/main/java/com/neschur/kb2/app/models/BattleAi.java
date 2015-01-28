package com.neschur.kb2.app.models;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.WarriorEntity;

public class BattleAi {
    private MapPointBattle[][] map;

    public void move(MapPointBattle[][] map) {
        this.map = map;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (isEntity(x, y) && !isFriendly(x, y))
                    step(map[x][y].getEntity());
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
                    boolean moved = moveWarTo(war, attacked.getX(), attacked.getY());
                    if (moved)
                        war.attack(attacked);
                }
        } else {
            WarriorEntity attacked = findNearestUserWar(war);

        }
    }

    private boolean moveWarTo(WarriorEntity war, int x, int y) {
        for (int i = (x - 1 < 0) ? 0 : x - 1; i <= ((x + 1 < 6) ? x + 1 : 6); i++) {
            for (int j = (y - 1 < 0) ? 0 : y - 1; j <= ((y + 1 < 5) ? y + 1 : 5); j++) {
                if (isLand(i, j) && !isEntity(i, j)) {
                    war.move(i, j);
                    return true;
                }
            }
        }
        return false;
    }

    private WarriorEntity findUserWar(boolean shoot) {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (isEntity(x, y) && isFriendly(x, y)) {
                    if (shoot) {
                        if (map[x][y].getEntity().isShoot())
                            return map[x][y].getEntity();
                    } else {
                        return map[x][y].getEntity();
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
                if(isEntity(i, j) && !isFriendly(i, j)) {
                    int d = distance(war, i, j);
                    if (d < distance) {
                        distance = d;
                        war = map[i][j].getEntity();
                    }
                }
            }
        }
        return dst;
    }

    private boolean isEntity(int x, int y) {
        return map[x][y].getEntity() != null;
    }

    private boolean isFriendly(int x, int y) {
        return map[x][y].getEntity().isFriendly();
    }

    private boolean isLand(int x, int y) {
        return map[x][y].getLand() == R.drawable.land;
    }

    private int distance(WarriorEntity selected, int x, int y) {
        return Math.max(Math.abs(selected.getX() - x), Math.abs(selected.getY() - y));
    }
}
