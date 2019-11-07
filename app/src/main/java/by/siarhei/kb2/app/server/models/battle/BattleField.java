package by.siarhei.kb2.app.server.models.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.countries.Country;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.entities.Fighting;
import by.siarhei.kb2.app.server.models.Glade;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Mover;
import by.siarhei.kb2.app.server.models.Player;
import by.siarhei.kb2.app.platforms.android.MainActivity;
import by.siarhei.kb2.app.server.models.iterators.EntityIterator;
import by.siarhei.kb2.app.server.models.iterators.MapPointsOwner;
import by.siarhei.kb2.app.server.warriors.Warrior;
import by.siarhei.kb2.app.server.warriors.WarriorSquad;

public class BattleField implements Glade {
    private final int XSize = 6;
    private final int YSize = 5;
    private final MapPointBattle[][] map;
    private MapPointBattle selected;
    private boolean aiTurn = false;

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
            System.out.println("select!!!");
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

    public void setSelected(WarriorEntity entity) {
        if(entity == null) {
            this.selected = null;
            return;
        }

        Iterator<MapPointBattle> iterator = getMapPointsList();
        while(iterator.hasNext()) {
            MapPointBattle point = iterator.next();
            if(point.getEntity() == entity) {
                this.selected = point;
                return;
            }
        }
    }

    public MapPointBattle getSelectedPoint() {
        return this.selected;
    }

    public Iterator<MapPointBattle> getMapPointsList() {
        return new Iterator<MapPointBattle>() {
            private int x = 0;
            private int y = 0;
            @Override
            public boolean hasNext() {
                return y < YSize;
            }

            @Override
            public MapPointBattle next() {
                if(y >= YSize)
                    return null;

                MapPointBattle point = map[x][y];
                if(x < XSize - 1) {
                    x++;
                } else {
                    x = 0;
                    y++;
                }
                return point;
            }
        };
    }

    public void setAiTurn(boolean aiTurn) {
        this.aiTurn = aiTurn;
    }

    public boolean isAiTurn() {
        return this.aiTurn;
    }

    public boolean finished() {
        boolean playerHasAnyone = false;
        boolean aiHasAnyone = false;

        Iterator<MapPointBattle> mapPoints = getMapPointsList();
        while(mapPoints.hasNext()) {
            MapPointBattle point = mapPoints.next();
            if(point.isEntity()) {
                playerHasAnyone = playerHasAnyone|| point.getEntity().isPlayerEntity();
                aiHasAnyone = aiHasAnyone || !point.getEntity().isPlayerEntity();
            }
        }

        return !(playerHasAnyone && aiHasAnyone);
    }
}
