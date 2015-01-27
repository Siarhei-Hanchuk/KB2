package com.neschur.kb2.app.models;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.entities.WarriorEntity;
import com.neschur.kb2.app.warriors.Warrior;

public class BattleField implements Glade {
    private MapPointBattle[][] map = new MapPointBattle[6][5];
    private Player player;
    private Fighting fighting;

    public BattleField(Player player, Fighting fighting) {
        this.player = player;
        this.fighting = fighting;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new MapPointBattle();
                map[i][j].setLand(R.drawable.land);
            }
        }

        prepareArmy();
    }

    @Override
    public MapPoint getMapPoint(int x, int y) {
        return map[x][y];
    }

    public MapPointBattle[][] getMapPoints() {
        return map;
    }

    private void prepareArmy() {
        System.out.println(player);
        System.out.println(fighting);
        for (int i = 0; i < 5; i++) {
            if (player.getWarriorSquad(i) != null)
                new WarriorEntity(this, 0, i, player.getWarriorSquad(i), true);
            if (fighting.getWarriorSquad(i) != null)
                new WarriorEntity(this, 5, i, fighting.getWarriorSquad(i), false);
        }
    }

    public void select(int x, int y) {
        if(map[x][y].getEntity()!= null && ((WarriorEntity)map[x][y].getEntity()).isFriendly()) {
            Warrior war = ((WarriorEntity) map[x][y].getEntity()).getWarrior();
            int step = war.getStep();
            moveArea(x, y, step);
        }
    }

    private void moveArea(int x, int y, int step){
        snake(x, y, step);
    }

    private void snake(int x, int y, int step) {
        if (step < 0 || x < 0 || x > 5 || y < 0 || y > 4)
            return;
        step--;
        map[x][y].setMove(true);
        snake(x + 1, y, step);
        snake(x - 1, y, step);
        snake(x, y + 1, step);
        snake(x, y - 1, step);
        snake(x + 1, y + 1, step);
        snake(x - 1, y - 1, step);
        snake(x - 1, y + 1, step);
        snake(x + 1, y - 1, step);
    }
}
