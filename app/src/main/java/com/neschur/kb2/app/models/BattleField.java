package com.neschur.kb2.app.models;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.entities.WarriorEntity;

public class BattleField implements Glade {
    private MapPointBattle[][] map = new MapPointBattle[6][5];
    private Player player;
    private Fighting fighting;
    private WarriorEntity selected;

    public BattleField(Player player, Fighting fighting) {
        this.player = player;
        this.fighting = fighting;

        prepareField();
        prepareArmy();
    }

    @Override
    public MapPoint getMapPoint(int x, int y) {
        return map[x][y];
    }

    @Override
    public MapPointBattle[][] getMapPoints() {
        return map;
    }


    public void prepareField() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new MapPointBattle();
                map[i][j].setLand(R.drawable.land);
            }
        }
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
        if (selected != null) {
            if (map[x][y].isMove()) {
                move(x, y);
            }
        } else {
            selectEntity(x, y);
        }
    }

    private void move(int x, int y) {
        if (map[x][y].getLand() == R.drawable.land && !isEntity(x, y)) {
            selected.reduceStep(distance(selected, x, y));
            selected.move(x, y);
            if (selected.getStep() > 0) {
                moveArea(x, y, selected);
            } else {
                clearMoveArea();
                selected = null;
            }
        } else {
            attack(x, y);
        }
    }

    private void attack(int x, int y) {
        if (isEntity(x, y) && !isFriendly(x, y)) {
            if (distance(selected, x, y) == 1 || selected.isShoot()) {
                selected.attack(map[x][y].getEntity());
                clearMoveArea();
                selected = null;
            }
        }
    }

    private void selectEntity(int x, int y) {
        if (map[x][y].getEntity() != null &&
                map[x][y].getEntity().isFriendly() &&
                map[x][y].getEntity().getStep() > 0) {
            selected = map[x][y].getEntity();
            WarriorEntity war = map[x][y].getEntity();
            moveArea(x, y, war);
        }
    }

    private void moveArea(int x, int y, WarriorEntity war) {
        clearMoveArea();
        snake(x, y, war.getStep());
        if (war.isShoot()) {
            shotGoals();
        }
    }

    private void clearMoveArea() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j].setMove(false);
            }
        }
    }

    private void shotGoals() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (isEntity(x, y) && !isFriendly(x, y)) {
                    map[x][y].setMove(true);
                }
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
        if (selected != null)
            return selected.getX();
        else
            return -1;
    }

    public int getSelectedY() {
        if (selected != null)
            return selected.getY();
        else
            return -1;
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
