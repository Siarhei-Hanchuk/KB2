package by.siarhei.kb2.app.server.countries;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.entities.ArmyShop;
import by.siarhei.kb2.app.server.entities.Castle;
import by.siarhei.kb2.app.server.entities.CastlesOwner;
import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.entities.Metro;
import by.siarhei.kb2.app.server.models.Glade;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.iterators.ArmyShopsOwner;
import by.siarhei.kb2.app.server.models.iterators.EntityIterator;
import by.siarhei.kb2.app.server.models.iterators.MapPointsOwner;

public class Country implements Glade, Serializable, ArmyShopsOwner, MapPointsOwner, CastlesOwner {
    public final static int MAX_MAP_SIZE = 65;
    private final MapPoint[][] map;
    private final Random random;
    private final int id;

    public Country(Random random, MapPoint[][] map, int id) {
        this.random = random;
        this.map = map;
        this.id = id;
    }

    @Override
    public MapPoint getMapPoint(int x, int y) {
        return map[x][y];
    }

    @Override
    public MapPoint[][] getMapPoints() {
        return map;
    }

    @Override
    public Iterator<ArmyShop> getArmyShops() {
        ArrayList<Iterator<ArmyShop>> iterators = new ArrayList<>();
        for (int i = 0; i < Country.MAX_MAP_SIZE; i++) {
            for (int j = 0; j < Country.MAX_MAP_SIZE; j++) {
                Entity entity = getMapPoint(i, j).getEntity();
                if(entity instanceof ArmyShop) {
                    iterators.add(((ArmyShop) entity).getArmyShops());
                }
            }
        }
        return new EntityIterator<>(iterators);
    }

    @Override
    public Iterator<MapPoint> getMapPointsList(Class type) {
        ArrayList<Iterator<MapPoint>> iterators = new ArrayList<>();
        for (int i = 0; i < Country.MAX_MAP_SIZE; i++) {
            for (int j = 0; j < Country.MAX_MAP_SIZE; j++) {
                Entity entity = getMapPoint(i, j).getEntity();
                if(type.isInstance(entity)) {
                    iterators.add(getMapPoint(i, j).getMapPointsList(type));
                }
            }
        }
        return new EntityIterator<>(iterators);
    }

    @Override
    public Iterator<Castle> getCastles() {
        return null;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean isEntity(int x, int y) {
        return map[x][y].getEntity() != null;
    }

    private boolean isLand(int x, int y) {
        return map[x][y].getLand() == R.drawable.land;
    }

    @Override
    public Entity getEntity(int x, int y) {
        return map[x][y].getEntity();
    }

    @Override
    public boolean inBorders(int x, int y) {
        return (x > 0 && y > 0 && x < MAX_MAP_SIZE && y < MAX_MAP_SIZE);
    }

    public void createMaps() {
        // TODO:
//        entityGenerator.updateSpell();
    }

    public MapPoint getLandNearCity() {
        return getLandNearPoint(getMapPointsList(City.class).next());
    }

    private MapPoint getLandNearPoint(MapPoint point) {
        int pointX = point.getX();
        int pointY = point.getY();
        for (int x = pointX - 1; x <= pointX + 1; x++) {
            for (int y = pointY - 1; y <= pointY + 1; y++) {
                if(x == pointX && y == pointY)
                    continue;
                if (getMapPoint(x, y).getLand() == R.drawable.land) {
                    return getMapPoint(x, y);
                }
            }
        }
        return point;
    }

    public MapPoint getRandomLand() {
        int x;
        int y;
        do {
            x = random.nextInt(MAX_MAP_SIZE);
            y = random.nextInt(MAX_MAP_SIZE);
        } while (isEntity(x, y) || !isLand(x, y));
        return getMapPoint(x, y);
    }

    public MapPoint getLinkedMetroPoint(Metro sourceMetro) {
        Iterator<MapPoint> metros = getMapPointsList(Metro.class);
        while(metros.hasNext()) {
            MapPoint mp = metros.next();
            if(mp.getEntity() != sourceMetro) {
                return getLandNearPoint(mp);
            }
        }
        return null;
    }
}

