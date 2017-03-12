package by.siarhei.kb2.app.models.battle;

import com.neschur.kb2.app.R;
import by.siarhei.kb2.app.controllers.BattleController;
import by.siarhei.kb2.app.entities.Fighting;
import by.siarhei.kb2.app.models.Glade;
import by.siarhei.kb2.app.models.Mover;
import by.siarhei.kb2.app.models.Player;

public class BattleField implements Glade {
    private final MapPointBattle[][] map = new MapPointBattle[6][5];
    private final Player player;
    private final Fighting fighting;
    private final BattleAi ai;
    private final BattleController battleController;
    private final Mover mover;
    private WarriorEntity selected;

    public BattleField(Player player, Fighting fighting, BattleController battleController) {
        this.player = player;
        this.fighting = fighting;
        this.ai = new BattleAi(this);
        this.battleController = battleController;
        this.mover = new Mover(this);

        prepareField();
        prepareArmy();
    }

    @Override
    public MapPointBattle getMapPoint(int x, int y) {
        return map[x][y];
    }

    @Override
    public MapPointBattle[][] getMapPoints() {
        return map;
    }


    public void prepareField() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = new MapPointBattle(this, i, j);
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
        if (!isEntity(x, y) || ignoreEntity) {
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

    @Override
    public boolean isEntity(int x, int y) {
        return map[x][y].getEntity() != null;
    }

    public boolean isFriendly(int x, int y) {
        return map[x][y].getEntity().isFriendly();
    }

    @Override
    public boolean isLand(int x, int y) {
        return map[x][y].getLand() == R.drawable.land;
    }

    @Override
    public boolean inBorders(int x, int y) {
        return (x > -1 && y > -1 && x < 6 && y < 5);
    }

    @Override
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
        battleController.updateView();
        aiControl();
        newPhase();
    }

    private void aiControl() {
        do {
            battleController.updateView(1000);
            ai.move();
        } while (!ai.isFinished() && friendlyCount() > 0);
        battleController.updateView();
    }

    private int friendlyCount() {
        int friendlyCount = 0;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (isEntity(x, y) && isFriendly(x, y))
                    friendlyCount++;
            }
        }
        return friendlyCount;
    }

    private void newPhase() {
        setSelected(null);
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
            battleController.battleFinish(false);
        }
        if (enemyCount == 0) {
            battleController.battleFinish(true);
        }
    }

    public void setSelected(WarriorEntity selected) {
        this.selected = selected;
    }
}
