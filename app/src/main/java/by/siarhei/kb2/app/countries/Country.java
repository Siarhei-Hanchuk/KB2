package by.siarhei.kb2.app.countries;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.entities.ArmyShop;
import by.siarhei.kb2.app.entities.Castle;
import by.siarhei.kb2.app.entities.CastlesOwner;
import by.siarhei.kb2.app.entities.City;
import by.siarhei.kb2.app.entities.Entity;
import by.siarhei.kb2.app.entities.Metro;
import by.siarhei.kb2.app.models.Glade;
import by.siarhei.kb2.app.models.MapPoint;
import by.siarhei.kb2.app.models.iterators.ArmyShopsOwner;
import by.siarhei.kb2.app.models.iterators.CitiesOwner;
import by.siarhei.kb2.app.models.iterators.EntityIterator;

public class Country implements Glade, Serializable, ArmyShopsOwner, CitiesOwner, CastlesOwner {
    public final static int MAX_MAP_SIZE = 65;
    final MapPoint[][] map;
    private final Random random;
    final int id;

    // TODO: fulfill lists
    private final ArrayList<City> cities = new ArrayList<>();
    private final ArrayList<ArmyShop> armyShops = new ArrayList<>();

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
        for (ArmyShopsOwner armyShop : armyShops) {
            iterators.add(armyShop.getArmyShops());
        }
        return new EntityIterator<>(iterators);
    }

    @Override
    public Iterator<City> getCities() {
        ArrayList<Iterator<City>> iterators = new ArrayList<>();
        for (CitiesOwner city : cities) {
            iterators.add(city.getCities());
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

    @Override
    public boolean isLand(int x, int y) {
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
        return getLandNearPoint(getCities().next().getMapPoint());
    }

    public MapPoint getLandNearPoint(MapPoint point) {
        for (int x = point.getX() - 1; x <= point.getX() + 1; x++) {
            for (int y = point.getY() - 1; y <= point.getY() + 1; y++) {
                if (point.getGlade().getMapPoint(x, y).getLand() == R.drawable.land) {
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

    public MapPoint getLinkedMetroPoint(Metro metro) {
//        TODO:
//        if (metro == metro1)
//            metro = metro2;
//        else
//            metro = metro1;
//        return getLandNearPoint(metro.getMapPoint());
        return null;
    }
}

