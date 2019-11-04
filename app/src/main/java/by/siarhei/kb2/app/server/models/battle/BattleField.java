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
    private final MapPointBattle[][] map;
    private MapPointBattle selected;
//    private WarriorSquad initPlayerArmyAtBattleField[] = new WarriorSquad[YSize];

    public BattleField(MapPointBattle[][] map) {
        this.map = map;
    }

    @Override
    public MapPointBattle getMapPoint(int x, int y) {
        return map[x][y];
    }

    @Override
    public MapPointBattle[][] getMapPoints() {
        return map;
    }

    public void selectEntity(int x, int y) {
        if (!inBorders(x,y)) return;

        MapPointBattle mapPoint = map[x][y];

        if (mapPoint.getEntity() == null) return;

        WarriorEntity entity = mapPoint.getEntity();

        if (entity.isPlayerEntity() && entity.getStep() > 0) {
            selected = mapPoint;
        }
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

    public int distance(MapPoint from, MapPoint to) {
        return Math.max(Math.abs(from.getX() - to.getX()),
                Math.abs(from.getY() - to.getY()));
    }

//    private int friendlyCount() {
//        int friendlyCount = 0;
//        for (int x = 0; x < XSize; x++) {
//            for (int y = 0; y < YSize; y++) {
//                if (isEntity(x, y) && isPlayerEntity(x, y))
//                    friendlyCount++;
//            }
//        }
//        return friendlyCount;
//    }

//    private HashMap<String, Integer> countPlayerCasualties() {
//        HashMap<String, Integer> survivors = new HashMap<>();
//        for (int x = 0; x < XSize; x++) {
//            for (int y = 0; y < YSize; y++) {
//                if (isEntity(x, y) && isPlayerEntity(x, y)) {
//                    survivors.put(getEntity(x,y).getTextId(), getEntity(x, y).getCount());
//                }
//            }
//        }
//        HashMap<String, Integer> army = new HashMap<>();
//        for (int index = 0; index < YSize; index ++) {
//            WarriorSquad warriorSquad = initPlayerArmyAtBattleField[index];
//            if (warriorSquad != null) {
//                army.put(warriorSquad.getWarrior().getTextId(), warriorSquad.getCount());
//            }
//        }
//
//        HashMap<String, Integer> casualties = new HashMap<>();
//        for (String id: army.keySet()) {
//            if (survivors.keySet().contains(id)) {
//                int squadCasualty = army.get(id) - survivors.get(id);
//                if (squadCasualty > 0) { // add to casualties only if have casualties
//                    casualties.put(id, squadCasualty);
//                }
//            } else { //not found - whole squad is dead
//                casualties.put(id, army.get(id));
//            }
//        }
//        return casualties;
//    }

    public void setSelected(MapPointBattle selected) {
        this.selected = selected;
    }

    public MapPointBattle getSelected() {
        return this.selected;
    }
}