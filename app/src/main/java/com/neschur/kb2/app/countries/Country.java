package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.generators.BaseGenerator;
import com.neschur.kb2.app.countries.generators.EntityGenerator;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Glade;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.iterators.ArmyShopsOwner;
import com.neschur.kb2.app.models.iterators.CitiesOwner;
import com.neschur.kb2.app.models.iterators.EntityIterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public abstract class Country implements Glade, Serializable, ArmyShopsOwner, CitiesOwner {
    public final static int MAX_MAP_SIZE = 65;
    final MapPoint[][] map;
    final BaseGenerator baseGenerator;
    final EntityGenerator entityGenerator;
    private final Random random;
    int id;

    Country(byte[] cityNamesMask) {
        random = new Random();
        baseGenerator = new BaseGenerator(this);
        entityGenerator = new EntityGenerator(this, cityNamesMask);

        map = new MapPoint[MAX_MAP_SIZE][MAX_MAP_SIZE];
        for (int i = 0; i < MAX_MAP_SIZE; i++) {
            for (int j = 0; j < MAX_MAP_SIZE; j++) {
                map[i][j] = new MapPoint(this, i, j);
            }
        }

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
        ArrayList<Iterator<ArmyShop>> iterators = new ArrayList<>();
        for (ArmyShopsOwner shop : entityGenerator.getArmyShops()) {
            iterators.add(shop.getArmyShops());
        }
        return new EntityIterator(iterators);
    }

    @Override
    public Iterator<City> getCities() {
        ArrayList<Iterator<City>> iterators = new ArrayList<>();
        for (City city : entityGenerator.getCities()) {
            iterators.add(city.getCities());
        }
        return new EntityIterator(iterators);
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

    public MapPoint getRandomLandNearCity() {
        City city = entityGenerator.getCities().get(0);
        int index = 0;
        while (index < entityGenerator.getCities().size()) {
            city = entityGenerator.getCities().get(index);
            index++;
            for (int x = city.getX() - 1; x <= city.getX() + 1; x++) {
                for (int y = city.getY() - 1; y <= city.getY() + 1; y++) {
                    if (city.getCountry().getMapPoint(x, y).getLand() == R.drawable.land) {
                        return getMapPoint(x, y);
                    }
                }
            }
        }
        return city.getMapPoint();
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
}

