package by.siarhei.kb2.app.server.models.battle;

import java.util.HashMap;
import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.entities.Fighting;
import by.siarhei.kb2.app.server.models.Glade;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Mover;
import by.siarhei.kb2.app.server.models.Player;
import by.siarhei.kb2.app.platforms.android.MainActivity;
import by.siarhei.kb2.app.server.warriors.Warrior;
import by.siarhei.kb2.app.server.warriors.WarriorSquad;

public class BattleField implements Glade {
    private final int XSize = 6;
    private final int YSize = 5;
    private final MapPointBattle[][] map = new MapPointBattle[XSize][YSize];
    private final Player player;
    private final Fighting fighting;
//    private final BattleAi ai;
    private final Mover mover;
    private MapPointBattle selected;
    private WarriorSquad initPlayerArmyAtBattleField[] = new WarriorSquad[YSize];
//    private boolean aiPhase = false;

    public BattleField(Player player, Fighting fighting) {
        this.player = player;
        this.fighting = fighting;
//        this.ai = new BattleAi(this);
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

//    private void attack(int x, int y) {
//        if (isEntity(x, y) && !isPlayerEntity(x, y)) {
//            if (distance(mapPointBySelected(), x, y) == 1 || selected.getEntity().isShoot()) {
//                selected.getEntity().attack(map[x][y].getEntity());
//                clearMoveArea();
//                selected = null;
//            }
//        }
//    }

    public MapPointBattle selectEntity(int x, int y) {
        if (!inBorders(x,y)) return null;

        MapPointBattle mapPoint = map[x][y];

        if (mapPoint.getEntity() == null) return null;

        WarriorEntity entity = mapPoint.getEntity();

        if (entity.isPlayerEntity() && entity.getStep() > 0) {
            selected = mapPoint;
            return selected;
//            moveArea(x, y, selected);
        }

        return null;
    }

    @Override
    public boolean isEntity(int x, int y) {
        return map[x][y].getEntity() != null;
    }

    public boolean isPlayerEntity(int x, int y) {
        return map[x][y].getEntity().isPlayerEntity();
    }

    @Override
    public boolean inBorders(int x, int y) {
        return (x > -1 && y > -1 && x < XSize && y < YSize);
    }

    @Override
    public WarriorEntity getEntity(int x, int y) {
        return map[x][y].getEntity();
    }

    public int distance(MapPoint from, int x, int y) {
        return Math.max(Math.abs(from.getX() - x),
                Math.abs(from.getY() - y));
    }

    public int distance(MapPoint from, MapPoint to) {
        return Math.max(Math.abs(from.getX() - to.getX()),
                Math.abs(from.getY() - to.getY()));
    }

    private int friendlyCount() {
        int friendlyCount = 0;
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
                if (isEntity(x, y) && isPlayerEntity(x, y))
                    friendlyCount++;
            }
        }
        return friendlyCount;
    }

    private HashMap<String, Integer> countPlayerCasualties() {
        HashMap<String, Integer> survivors = new HashMap<>();
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
                if (isEntity(x, y) && isPlayerEntity(x, y)) {
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

    public void setSelected(MapPointBattle selected) {
        this.selected = selected;
    }

//    public boolean isAiPhase() {
//        return aiPhase;
//    }

    private MapPoint mapPointBySelected() {
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
                if (isEntity(x, y) && getMapPoint(x, y) == selected) {
                    return getMapPoint(x, y);
                }
            }
        }

        return null;
    }

    public boolean hasSelected() {
        return selected != null;
    }

    public MapPointBattle getSelected() {
        return this.selected;
    }
}
