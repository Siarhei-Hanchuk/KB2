package com.neschur.kb2.app.models;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.WarriorEntity;

/**
 * Created by siarhei on 28.01.15.
 */
public class BattleAi {
    private MapPointBattle[][] map;

    public void move(MapPointBattle[][] map) {
        this.map = map;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (!isFriendly(x, y))
                    step(map[x][y].getEntity());
            }
        }
    }

    private void step(WarriorEntity war) {
        if (war.isShoot()) {
            WarriorEntity attacked = findUserWar(true);

            if (attacked != null)
                war.attack(attacked);
        }
    }

    private WarriorEntity findUserWar(boolean shoot) {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (shoot) {
                    if (isFriendly(x, y) && map[x][y].getEntity().isShoot()) {
                        return map[x][y].getEntity();
                    }
                }
                else {
                    if (isFriendly(x, y)) {
                        return map[x][y].getEntity();
                    }
                }
            }
        }
        return null;
    }

    private boolean isFriendly(int x, int y) {
        return map[x][y].getEntity() != null && map[x][y].getEntity().isFriendly();
    }
}
