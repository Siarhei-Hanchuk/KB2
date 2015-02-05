package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Captain;
import com.neschur.kb2.app.entities.Castle;
import com.neschur.kb2.app.entities.CastleLeft;
import com.neschur.kb2.app.entities.CastleRight;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.GuidePost;
import com.neschur.kb2.app.entities.MapNext;
import com.neschur.kb2.app.entities.Sorcerer;
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
    private final Random random;
    private final ArrayList<ArmyShopsOwner> armyShops = new ArrayList<>();
    private final ArrayList<City> cities = new ArrayList<>();
    private final byte[] cityNamesMask;
    int id;
    private Sorcerer sorcerer;

    Country(byte[] cityNamesMask) {
        this.cityNamesMask = cityNamesMask;
        this.random = new Random();

        map = new MapPoint[65][65];
        for (int i = 0; i < 65; i++) {
            for (int j = 0; j < 65; j++) {
                map[i][j] = new MapPoint(this, i, j);
            }
        }

        base();
    }

    void createCaptain(int x, int y) {
        Fighting captain = new Captain(getMapPoint(x, y));
        int authority = 100 + rand(100);
        captain.generateArmy(authority, 0);
    }

    private void createSorcerer(int x, int y) {
        sorcerer = new Sorcerer(getMapPoint(x, y));
    }

    public Sorcerer getSorcerer() {
        return sorcerer;
    }

    int rand(int n) {
        return random.nextInt(n);
    }

    @Override
    public MapPoint getMapPoint(int x, int y) {
        return map[x][y];
    }

    public MapPoint[][] getMapPoints() {
        return map;
    }

    public int getId() {
        return id;
    }

    private void base() {
        for (int i = 0; i < MAX_MAP_SIZE; i++) {
            for (int j = 0; j < MAX_MAP_SIZE; j++) {
                map[i][j].setLand(R.drawable.water);
            }
        }
        for (int i = 5; i < MAX_MAP_SIZE - 5; i++) {
            for (int j = 5; j < MAX_MAP_SIZE - 5; j++) {
                map[i][j].setLand(R.drawable.land);
            }
        }
    }

    void createCity(MapPoint mp) {
        int nameId = 0;
        for (int i = 0; i < cityNamesMask.length; i++) {
            if (cityNamesMask[i] == 1) {
                nameId = i + 1;
                cityNamesMask[i] = -1;
                break;
            }
        }
        City city = new City(mp, nameId);
        cities.add(city);
        mp.setEntity(city);
    }

    void cities() {
        int count = 0;
        int x;
        int y;
        while (count < 5) {
            x = rand(MAX_MAP_SIZE - 1);
            y = rand(MAX_MAP_SIZE - 1);

            if (((map[x + 1][y].getLand() == R.drawable.water)
                    || (map[x - 1][y].getLand() == R.drawable.water)
                    || (map[x][y + 1].getLand() == R.drawable.water)
                    || (map[x][y - 1].getLand() == R.drawable.water))
                    && ((map[x][y].getLand() == R.drawable.land)
                    && (map[x][y].getEntity() == null))) {
                createCity(getMapPoint(x, y));
                count++;
            }
        }
    }

    void guidePosts() {
        int count = 0;
        while (count < 5) {
            int y = rand(54) + 5;
            int x = rand(54) + 5;
            if ((map[x][y].getLand() == R.drawable.land)
                    || (map[x][y].getLand() == R.drawable.sand)) {
                map[x][y].setEntity(new GuidePost(getMapPoint(x, y)));
            }
            count++;
        }
    }

    void goldChests(int frequency, int mode) {
        for (int i = 5; i < MAX_MAP_SIZE - 5; i++) {
            for (int j = 5; j < MAX_MAP_SIZE - 5; j++) {
                if (((map[i][j].getLand() == R.drawable.land)
                        || (map[i][j].getLand() == R.drawable.sand))
                        && (map[i][j].getEntity() == null)) {
                    if (rand(frequency) == 1) {
                        map[i][j].setEntity(new GoldChest(getMapPoint(i, j), mode));
                    }
                }
            }
        }
    }

    private void createRiver(int x, int y, int direction, int length, int type) {
        int run = 0;
        int width;
        while (run < length) {
            run++;
            width = rand(3);
            for (int coordinate = -width - 1; coordinate < +width + 1; coordinate++) {
                if (type == 1) {
                    map[x][y + coordinate].setLand(R.drawable.water);
                } else {
                    map[x + coordinate][y].setLand(R.drawable.water);
                }
            }
            int center;
            int route;
            if (type == 1) {
                center = y;
                route = x;
            } else {
                center = x;
                route = y;
            }
            center = center + rand(5) - 2;
            if (center > 62)
                center = 62;
            if (center < 3)
                center = 3;
            route += direction;
            if (type == 1) {
                y = center;
                x = route;
            } else {
                x = center;
                y = route;
            }
        }
    }

    void river(int length) {
        int start1 = rand(53) + 5;
        int r = rand(4);
        switch (r) {
            case 0:
                createRiver(5, start1, 1, length, 1);
                break;
            case 1:
                createRiver(59, start1, -1, length, 1);
                break;
            case 2:
                createRiver(start1, 5, 1, length, 2);
                break;
            case 3:
                createRiver(start1, 59, -1, length, 2);
                break;
            default:
                break;
        }
    }

    void landscape(double frequency, int land) {
        for (int i = 5; i < MAX_MAP_SIZE - 5; i++) {
            for (int j = 5; j < MAX_MAP_SIZE - 5; j++) {
                if (Math.random() < frequency) {
                    if ((map[i][j].getLand() == R.drawable.land)
                            && (map[i][j].getEntity() == null)) {
                        map[i][j].setLand(land);
                    }
                }
            }
        }
    }

    void mapNext() {
        int x;
        int y;
        do {
            y = rand(54) + 5;
            x = rand(54) + 5;
        } while (map[x][y].getLand() != R.drawable.land && map[x][y].getEntity() == null);
        map[x][y].setEntity(new MapNext(getMapPoint(x, y)));
    }

    private Castle tryPlaceCastle(int x, int y) {
        if (((map[x][y].getLand() == R.drawable.land) &&
                (map[x - 1][y].getLand() == R.drawable.land) &&
                (map[x + 1][y].getLand() == R.drawable.land) &&
                (map[x][y + 1].getLand() == R.drawable.land)) &&
                ((map[x][y].getEntity() == null) &&
                        (map[x - 1][y].getEntity() == null) &&
                        (map[x + 1][y].getEntity() == null) &&
                        (map[x][y + 1].getEntity() == null))) {
            Castle castle = new Castle(getMapPoint(x, y));
            new CastleRight(getMapPoint(x + 1, y));
            new CastleLeft(getMapPoint(x - 1, y));
            createCaptain(x, y + 1);
            return castle;
        }
        return null;
    }

    void castles() {
        int count = 0;
        while (count < 5) {
            Castle castle = tryPlaceCastle(rand(MAX_MAP_SIZE), rand(MAX_MAP_SIZE));
            if (castle != null) {
                castle.generateArmy(1000, 0);
                count++;
            }
        }
    }

    private boolean tryPlaceCaptain(int x, int y) {
        if (map[x][y].getLand() == R.drawable.land && map[x][y].getEntity() == null) {
            map[x][y].setEntity(new Captain(getMapPoint(x, y)));
            return true;
        }
        return false;
    }

    private void captains() {
        int count = 5;
        while (count < 20) {
            if (tryPlaceCaptain(rand(MAX_MAP_SIZE), rand(MAX_MAP_SIZE)))
                count++;
        }
    }

    void createArmy(MapPoint mp, int group) {
        ArmyShop shop = new ArmyShop(mp, group);
        armyShops.add(shop);
        mp.setEntity(shop);
    }

    void army(int count, int group) {
        int run = 0;
        while (run < count) {
            int x = rand(65);
            int y = rand(65);
            MapPoint mp = getMapPoint(x, y);
            if (mp.getEntity() == null && mp.getLand() == R.drawable.land) {
                createArmy(mp, group);
                run++;
            }
        }
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
        City city = cities.get(0);
        int index = 0;
        while (index < cities.size()) {
            city = cities.get(index);
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
            x = rand(MAX_MAP_SIZE);
            y = rand(MAX_MAP_SIZE);
        } while (isEntity(x, y) || !isLand(x, y));
        return getMapPoint(x, y);
    }

    @Override
    public Iterator<ArmyShop> getArmyShops() {
        ArrayList<Iterator<ArmyShop>> iterators = new ArrayList<>();
        for (ArmyShopsOwner shop : armyShops) {
            iterators.add(shop.getArmyShops());
        }
        return new EntityIterator(iterators);
    }

    @Override
    public Iterator<City> getCities() {
        ArrayList<Iterator<City>> iterators = new ArrayList<>();
        for (City city : cities) {
            iterators.add(city.getCities());
        }
        return new EntityIterator(iterators);
    }
}

