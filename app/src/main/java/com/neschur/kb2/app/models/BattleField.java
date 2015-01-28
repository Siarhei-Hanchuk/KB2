package com.neschur.kb2.app.models;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.entities.WarriorEntity;
import com.neschur.kb2.app.warriors.Warrior;

public class BattleField implements Glade {
    private MapPointBattle[][] map = new MapPointBattle[6][5];
    private Player player;
    private Fighting fighting;
    private WarriorEntity selected;
    private int selectedX = -1;
    private int selectedY = -1;

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
        for (int i = 0; i < 5; i++) {
            if (player.getWarriorSquad(i) != null)
                new WarriorEntity(this, 0, i,
                        player.getWarriorSquad(i).getWarrior(),
                        player.getWarriorSquad(i).getCount(),
                        true);
            if (fighting.getWarriorSquad(i) != null)
                new WarriorEntity(this, 5, i,
                        fighting.getWarriorSquad(i).getWarrior(),
                        fighting.getWarriorSquad(i).getCount(),
                        false);
        }
    }

    public void select(int x, int y) {
        if(selected != null) {
            if(map[x][y].isMove()) {
                if (map[x][y].getLand() == R.drawable.land && map[x][y].getEntity() == null) {
                    selected.move(x, y);
                    selected.reduceStep(Math.max(Math.abs(selectedX - x), Math.abs(selectedY - y)));
                    selectedX = x;
                    selectedY = y;
                    if (selected.getStep() > 0) {
                        moveArea(x, y, selected.getStep());
                    } else {
                        clearMoveArea();
                        selected = null;
                        selectedX = -1;
                        selectedY = -1;
                    }
                } else if (map[x][y].getEntity() != null && !map[x][y].getEntity().isFriendly()){
                    if (Math.max(Math.abs(selectedX - x), Math.abs(selectedY - y)) == 1){

                    }
                }
            }
        } else if(map[x][y].getEntity()!= null &&
                map[x][y].getEntity().isFriendly() &&
                map[x][y].getEntity().getStep() > 0) {
            selected = map[x][y].getEntity();
            selectedX = x;
            selectedY = y;
            WarriorEntity war = map[x][y].getEntity();
            moveArea(x, y, war.getStep());
        }
    }

    private void moveArea(int x, int y, int step){
        clearMoveArea();
        snake(x, y, step);
    }

    private void clearMoveArea() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j].setMove(false);
            }
        }
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

    public int getSelectedX() {
        return selectedX;
    }

    public int getSelectedY() {
        return selectedY;
    }
}
