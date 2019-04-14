package by.siarhei.kb2.app.server.models.battle;

import java.util.HashMap;
import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.controllers.BattleController;
import by.siarhei.kb2.app.server.entities.Fighting;
import by.siarhei.kb2.app.server.models.Glade;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Mover;
import by.siarhei.kb2.app.server.models.Player;
import by.siarhei.kb2.app.platforms.android.MainActivity;
import by.siarhei.kb2.app.server.warriors.WarriorSquad;

public class BattleField implements Glade {
    private final int XSize = 6;
    private final int YSize = 5;
    private final MapPointBattle[][] map = new MapPointBattle[XSize][YSize];
    private final Player player;
    private final Fighting fighting;
    private final BattleAi ai;
    private final Mover mover;
    private WarriorEntity selected;
    private WarriorSquad initPlayerArmyAtBattleField[] = new WarriorSquad[YSize];

    public BattleField(Player player, Fighting fighting) {
        this.player = player;
        this.fighting = fighting;
        this.ai = new BattleAi(this);
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
        for (int i = 0; i < XSize; i++) {
            for (int j = 0; j < YSize; j++) {
                map[i][j] = new MapPointBattle(this, i, j);
                map[i][j].setLand(R.drawable.land);
            }
        }
    }

    private void prepareArmy() {
        Integer plSquadsIndex = 0;
        for (int squadIndex = 0; squadIndex < player.MAX_ARMY && plSquadsIndex < YSize; squadIndex++) {
            if (player.getWarriorSquad(squadIndex) != null) {
                initPlayerArmyAtBattleField[plSquadsIndex] = player.getWarriorSquad(squadIndex);
                plSquadsIndex++;
            }
        }
        for (int i = 0; i < YSize; i++) {
            if (initPlayerArmyAtBattleField[i] != null)
                new WarriorEntity(getMapPoint(0, i),
                        initPlayerArmyAtBattleField[i].getWarrior(),
                        initPlayerArmyAtBattleField[i].getCount(),
                        true);
            if (fighting.getWarriorSquad(i) != null)
                new WarriorEntity(getMapPoint(5, i),
                        fighting.getWarriorSquad(i).getWarrior(),
                        fighting.getWarriorSquad(i).getCount(),
                        false);
        }
    }

    public boolean moveTo(int x, int y) {
        if (!map[x][y].isMove()) {
            return false;
        }
        MapPoint mapPoint = mapPointBySelected();
        if (isLand(x, y) && !isEntity(x, y)) {
            selected.reduceStep(distance(mapPoint, x, y));
            mover.teleport(selected, mapPoint, getMapPoint(x, y));
            if (selected.getStep() > 0) {
                moveArea(x, y, selected);
            } else {
                clearMoveArea();
                selected = null;
            }
        } else {
            attack(x, y);
        }

        return tryFinishPhase();
    }

    private void attack(int x, int y) {
        if (isEntity(x, y) && !isFriendly(x, y)) {
            if (distance(mapPointBySelected(), x, y) == 1 || selected.isShoot()) {
                selected.attack(map[x][y].getEntity());
                clearMoveArea();
                selected = null;
            }
        }
    }

    public void selectEntity(int x, int y) {
        if (!inBorders(x,y)) {
            MainActivity.showToast("Out of battlefield zone");
            return;
        }
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
        else {
            snake(x, y, war.getStep());
        }
        if (war.isShoot())
            shotGoals();
    }

    private void clearMoveArea() {
        for (int i = 0; i < XSize; i++) {
            for (int j = 0; j < YSize; j++) {
                map[i][j].setMove(false);
            }
        }
    }

    private void shotGoals() {
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
                if (isEntity(x, y) && !isFriendly(x, y)) {
                    map[x][y].setMove(true);
                }
            }
        }
    }

    private void moveAreaFly() {
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
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
        if (step < 0 || x < 0 || x > (XSize - 1) || y < 0 || y > (YSize - 1) ||
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
//        TODO:
//        if (selected != null)
//            return selected.getMapPoint().getX();
//        else
//            return -1;
        return -1;
    }

    public int getSelectedY() {
//        TODO:
//        if (selected != null)
//            return selected.getMapPoint().getY();
//        else
//            return -1;
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
        return (x > -1 && y > -1 && x < XSize && y < YSize);
    }

    @Override
    public WarriorEntity getEntity(int x, int y) {
        return map[x][y].getEntity();
    }

    private int distance(MapPoint from, int x, int y) {
        return Math.max(Math.abs(from.getX() - x),
                Math.abs(from.getY() - y));
    }

    private boolean tryFinishPhase() {
        boolean finish = true;
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
                if (isEntity(x, y) && isFriendly(x, y)) {
                    if (map[x][y].getEntity().getStep() > 0)
                        finish = false;
                }
            }
        }
        if(finish) {
            newPhase(true);
            return true;
        }
        return false;
    }

    public boolean aiControl() {
        ai.move();
        return ai.isFinished() || friendlyCount() == 0;
    }

    private int friendlyCount() {
        int friendlyCount = 0;
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
                if (isEntity(x, y) && isFriendly(x, y))
                    friendlyCount++;
            }
        }
        return friendlyCount;
    }

    private void newPhase(boolean reset) {
        setSelected(null);
        int friendlyCount = 0;
        int enemyCount = 0;
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
                if (isEntity(x, y)) {
                    if(reset)
                        map[x][y].getEntity().resetStep();
                    if (isFriendly(x, y)) {
                        friendlyCount++;
                    } else {
                        enemyCount++;
                    }
                }
            }
        }
        if (friendlyCount == 0 || enemyCount == 0) {
//            battleController.battleFinish(enemyCount == 0, countPlayerCasualties());
        }
    }

    private HashMap<String, Integer> countPlayerCasualties() {
        HashMap<String, Integer> survivors = new HashMap<>();
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
                if (isEntity(x, y) && isFriendly(x, y)) {
                    survivors.put(getEntity(x,y).getTextId(), getEntity(x, y).getCount());
                }
            }
        }
        HashMap<String, Integer> army = new HashMap<>();
        for (int index = 0; index < YSize; index ++) {
            WarriorSquad warriorSquad = initPlayerArmyAtBattleField[index];
            if (warriorSquad != null) {
                army.put(warriorSquad.getWarrior().getTextId(), warriorSquad.getCount());
            }
        }

        HashMap<String, Integer> casualties = new HashMap<>();
        for (String id: army.keySet()) {
            if (survivors.keySet().contains(id)) {
                int squadCasualty = army.get(id) - survivors.get(id);
                if (squadCasualty > 0) { // add to casualties only if have casualties
                    casualties.put(id, squadCasualty);
                }
            } else { //not found - whole squad is dead
                casualties.put(id, army.get(id));
            }
        }
        return casualties;
    }

    public void setSelected(WarriorEntity selected) {
        this.selected = selected;
    }

    private MapPoint mapPointBySelected() {
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
                if (isEntity(x, y) && getMapPoint(x, y).getEntity() == selected) {
                    return getMapPoint(x, y);
                }
            }
        }

        return null;
    }

    public boolean hasSelected() {
        return selected != null;
    }
}
