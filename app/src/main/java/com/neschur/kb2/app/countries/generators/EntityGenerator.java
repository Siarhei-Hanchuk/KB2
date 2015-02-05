package com.neschur.kb2.app.countries.generators;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Captain;
import com.neschur.kb2.app.entities.Castle;
import com.neschur.kb2.app.entities.CastleLeft;
import com.neschur.kb2.app.entities.CastleRight;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.GuidePost;
import com.neschur.kb2.app.entities.MapNext;
import com.neschur.kb2.app.models.Glade;
import com.neschur.kb2.app.models.MapPoint;

import java.util.ArrayList;
import java.util.Random;

public class EntityGenerator {
    private final Random random;
    private final ArrayList<ArmyShop> armyShops = new ArrayList<>();
    private final ArrayList<City> cities = new ArrayList<>();
    private final MapPoint[][] map;
    private final byte[] cityNamesMask;

    public EntityGenerator(Glade country, byte[] cityNamesMask) {
        this.cityNamesMask = cityNamesMask;
        this.random = new Random();
        this.map = country.getMapPoints();
    }

    public void cities() {
        int count = 0;
        int x;
        int y;
        while (count < 5) {
            x = random.nextInt(Country.MAX_MAP_SIZE - 1);
            y = random.nextInt(Country.MAX_MAP_SIZE - 1);

            if (((map[x + 1][y].getLand() == R.drawable.water)
                    || (map[x - 1][y].getLand() == R.drawable.water)
                    || (map[x][y + 1].getLand() == R.drawable.water)
                    || (map[x][y - 1].getLand() == R.drawable.water))
                    && ((map[x][y].getLand() == R.drawable.land)
                    && (map[x][y].getEntity() == null))) {
                createCity(map[x][y]);
                count++;
            }
        }
    }

    private void createCity(MapPoint mp) {
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

    public void guidePosts() {
        int count = 0;
        while (count < 5) {
            int y = random.nextInt(54) + 5;
            int x = random.nextInt(54) + 5;
            if ((map[x][y].getLand() == R.drawable.land)
                    || (map[x][y].getLand() == R.drawable.sand)) {
                map[x][y].setEntity(new GuidePost(map[x][y]));
            }
            count++;
        }
    }

    public void goldChests(int frequency, int mode) {
        for (int i = 5; i < Country.MAX_MAP_SIZE - 5; i++) {
            for (int j = 5; j < Country.MAX_MAP_SIZE - 5; j++) {
                if (((map[i][j].getLand() == R.drawable.land)
                        || (map[i][j].getLand() == R.drawable.sand))
                        && (map[i][j].getEntity() == null)) {
                    if (random.nextInt(frequency) == 1) {
                        map[i][j].setEntity(new GoldChest(map[i][j], mode));
                    }
                }
            }
        }
    }

    public void castles() {
        int count = 0;
        while (count < 5) {
            Castle castle = tryPlaceCastle(random.nextInt(Country.MAX_MAP_SIZE),
                    random.nextInt(Country.MAX_MAP_SIZE));
            if (castle != null) {
                castle.generateArmy(1000, 0);
                count++;
            }
        }
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
            Castle castle = new Castle(map[x][y]);
            new CastleRight(map[x + 1][y]);
            new CastleLeft(map[x - 1][y]);
            tryPlaceCastle(x, y + 1);
            return castle;
        }
        return null;
    }


    public void captains() {
        int count = 0;
        while (count < 20) {
            if (tryPlaceCaptain(random.nextInt(Country.MAX_MAP_SIZE),
                    random.nextInt(Country.MAX_MAP_SIZE)))
                count++;
        }
    }

    private boolean tryPlaceCaptain(int x, int y) {
        if (map[x][y].getLand() == R.drawable.land && map[x][y].getEntity() == null) {
            Fighting captain = new Captain(map[x][y]);
            int authority = 100 + random.nextInt(100);
            captain.generateArmy(authority, 0);
            return true;
        }
        return false;
    }

    public void armies(int count, int group) {
        int run = 0;
        while (run < count) {
            MapPoint mp = map[random.nextInt(65)][random.nextInt(65)];
            if (mp.getEntity() == null && mp.getLand() == R.drawable.land) {
                createArmy(mp, group);
                run++;
            }
        }
    }

    private void createArmy(MapPoint mp, int group) {
        ArmyShop shop = new ArmyShop(mp, group);
        armyShops.add(shop);
        mp.setEntity(shop);
    }

    public void mapNext() {
        int x;
        int y;
        do {
            y = random.nextInt(54) + 5;
            x = random.nextInt(54) + 5;
        } while (map[x][y].getLand() != R.drawable.land && map[x][y].getEntity() == null);
        new MapNext(map[x][y]);
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public ArrayList<ArmyShop> getArmyShops() {
        return armyShops;
    }
}
