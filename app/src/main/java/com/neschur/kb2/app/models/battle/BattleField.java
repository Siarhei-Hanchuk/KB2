package com.neschur.kb2.app.models.battle;

import com.neschur.kb2.app.models.Mover;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.models.Glade;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.Player;

public class BattleField implements Glade {
    private MapPointBattle[][] map = new MapPointBattle[6][5];
    private Player player;
    private Fighting fighting;
    private WarriorEntity selected;
    private BattleAi ai;
    private BattleController battleFinishing;
    private Mover mover;

    public BattleField(Player player, Fighting fighting, BattleController battleFinishing) {
        this.player = player;
        this.fighting = fighting;
        this.ai = new BattleAi(this);
        this.battleFinishing = battleFinishing;
        this.mover = new Mover(this);

        prepareField();
        prepareArmy();
    }

    @Override
    public MapPoint getMapPoint(int x, int y) {
        return map[x][y];
    }

    public MapPointBattle[][] getMapPoints() {
        return map;
    }


    public void prepareField() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new MapPointBattle(this, i ,j);
                map[i][j].setLand(R.drawable.land);
            }
        }
    }

    private void prepareArmy() {
        for (int i = 0; i < 5; i++) {
            if (player.getWarriorSquad(i) != null)
                new WarriorEntity(getMapPoint(0, i),
                        player.getWarriorSquad(i).getWarrior(),
                        player.getWarriorSquad(i).getCount(),
                        true);
            if (fighting.getWarriorSquad(i) != null)
                new WarriorEntity(getMapPoint(5, i),
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
        if (isLand(x, y) && !isEntity(x, y)) {
            selected.reduceStep(distance(selected, x, y));
            mover.teleport(selected, getMapPoint(x, y));
            if (selected.getStep() > 0) {
                moveArea(x, y, selected);
            } else {
                clearMoveArea();
                selected = null;
            }
        } else {
            attack(x, y);
        }

        tryFinishPhase();
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
            moveArea(x, y, selected);
        }
    }

    private void moveArea(int x, int y, WarriorEntity war) {
        clearMoveArea();
        if (war.isFly())
            moveAreaFly();
        else
            snake(x, y, war.getStep());
        if (war.isShoot())
            shotGoals();
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

    private void moveAreaFly() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (isLand(x, y) || (isEntity(x, y) && !isFriendly(x, y))) {
                    map[x][y].setMove(true);
                }
            }
        }
    }

    private void snake(int x, int y, int step) {
        snake(x, y, step, true);
    }

    private void snake(int x, int y, int step, boolean ignoreEntity) {
        if (step < 0 || x < 0 || x > 5 || y < 0 || y > 4 ||
                !isLand(x, y))
            return;
        step--;
        map[x][y].setMove(true);
        if(!isEntity(x, y) || ignoreEntity) {
            snake(x + 1, y, step, false);
            snake(x - 1, y, step, false);
            snake(x, y + 1, step, false);
            snake(x, y - 1, step, false);
            snake(x + 1, y + 1, step, false);
            snake(x - 1, y - 1, step, false);
            snake(x - 1, y + 1, step, false);
            snake(x + 1, y - 1, step, false);
        }
    }

    public int getSelectedX() {
        if (selected != null)
            return selected.getMapPoint().getX();
        else
            return -1;
    }

    public int getSelectedY() {
        if (selected != null)
            return selected.getMapPoint().getY();
        else
            return -1;
    }

    public boolean isEntity(int x, int y) {
        return map[x][y].getEntity() != null;
    }

    public boolean isFriendly(int x, int y) {
        return map[x][y].getEntity().isFriendly();
    }

    public boolean isLand(int x, int y) {
        return map[x][y].getLand() == R.drawable.land;
    }

    public WarriorEntity getEntity(int x, int y) {
        return map[x][y].getEntity();
    }

    private int distance(WarriorEntity selected, int x, int y) {
        return Math.max(Math.abs(selected.getMapPoint().getX() - x),
                Math.abs(selected.getMapPoint().getY() - y));
    }

    private void tryFinishPhase() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (isEntity(x, y) && isFriendly(x, y)) {
                    if (map[x][y].getEntity().getStep() > 0)
                        return;
                }
            }
        }
        ai.move();
        newPhase();
    }

    private void newPhase() {
        int friendlyCount = 0;
        int enemyCount = 0;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (isEntity(x, y)) {
                    map[x][y].getEntity().resetStep();
                    if (isFriendly(x, y)) {
                        friendlyCount++;
                    } else {
                        enemyCount++;
                    }
                }
            }
        }
        if (friendlyCount == 0) {
            battleFinishing.battleFinish(false);
        }
        if (enemyCount == 0) {
            battleFinishing.battleFinish(true);
        }
    }
}
