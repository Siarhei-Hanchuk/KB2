package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.generators.BaseGenerator;
import com.neschur.kb2.app.countries.generators.EntityGenerator;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Castle;
import com.neschur.kb2.app.entities.CastlesOwner;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.Metro;
import com.neschur.kb2.app.models.Glade;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.iterators.ArmyShopsOwner;
import com.neschur.kb2.app.models.iterators.CitiesOwner;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;

public abstract class Country implements Glade, Serializable, ArmyShopsOwner, CitiesOwner,
        CastlesOwner {
    public final static int MAX_MAP_SIZE = 65;
    final MapPoint[][] map;
    final BaseGenerator baseGenerator;
    final EntityGenerator entityGenerator;
    private final Random random;
    int id;
    private Metro metro1;
    private Metro metro2;

    Country() {
        random = new Random();

        map = new MapPoint[MAX_MAP_SIZE][MAX_MAP_SIZE];
        for (int i = 0; i < MAX_MAP_SIZE; i++) {
            for (int j = 0; j < MAX_MAP_SIZE; j++) {
                map[i][j] = new MapPoint(this, i, j);
            }
        }

        baseGenerator = new BaseGenerator(this);
        entityGenerator = new EntityGenerator(this);

        baseGenerator.base();
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
        return entityGenerator.getArmyShops();
    }

    @Override
    public Iterator<City> getCities() {
        return entityGenerator.getCities();
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

    public void createMetro() {
        metro1 = entityGenerator.metro();
        metro2 = entityGenerator.metro();
    }

    public void createMaps() {
        entityGenerator.clearMaps();
        if (Math.random() < 0.1)
            entityGenerator.goodMap();
        if (Math.random() < 0.1)
            entityGenerator.harmfulMap();
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
        if(metro == metro1)
            metro = metro2;
        else
            metro = metro1;
        return getLandNearPoint(metro.getMapPoint());
    }
}

