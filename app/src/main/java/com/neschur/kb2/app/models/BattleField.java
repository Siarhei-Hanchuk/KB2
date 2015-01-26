package com.neschur.kb2.app.models;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.entities.WarriorEntity;

public class BattleField implements Glade {
    private MapPoint[][] map = new MapPoint[6][5];
    private Player player;
    private Fighting fighting;

    public BattleField(Player player, Fighting fighting) {
        this.player = player;
        this.fighting = fighting;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new MapPoint();
                map[i][j].setLand(R.drawable.land);
            }
        }

        prepareArmy();
    }

    @Override
    public MapPoint getMapPoint(int x, int y) {
        return map[x][y];
    }

    private void prepareArmy() {
        System.out.println(player);
        System.out.println(fighting);
        for (int i = 0; i < 5; i++) {
            if (player.getWarriorSquad(i) != null)
                new WarriorEntity(this, 0, i, player.getWarriorSquad(i).getWarrior(), true);
            if (fighting.getWarriorSquad(i) != null)
                new WarriorEntity(this, 5, i, fighting.getWarriorSquad(i).getWarrior(), false);
        }
    }
}
