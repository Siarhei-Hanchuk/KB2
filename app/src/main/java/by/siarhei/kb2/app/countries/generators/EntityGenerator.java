package by.siarhei.kb2.app.countries.generators;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.countries.Country;
import by.siarhei.kb2.app.entities.ArmyShop;
import by.siarhei.kb2.app.entities.Captain;
import by.siarhei.kb2.app.entities.Castle;
import by.siarhei.kb2.app.entities.CastleLeft;
import by.siarhei.kb2.app.entities.CastleRight;
import by.siarhei.kb2.app.entities.City;
import by.siarhei.kb2.app.entities.Fighting;
import by.siarhei.kb2.app.entities.GoldChest;
import by.siarhei.kb2.app.entities.GuidePost;
import by.siarhei.kb2.app.entities.MapNext;
import by.siarhei.kb2.app.entities.Metro;
import by.siarhei.kb2.app.entities.Spell;
import by.siarhei.kb2.app.models.Glade;
import by.siarhei.kb2.app.models.MapPoint;
import by.siarhei.kb2.app.models.iterators.ArmyShopsOwner;
import by.siarhei.kb2.app.models.iterators.CitiesOwner;
import by.siarhei.kb2.app.models.iterators.EntityIterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class EntityGenerator implements CitiesOwner, ArmyShopsOwner, Serializable {
    private static byte[] cityNamesMask;
    private static byte[] castleNamesMask;
    private final Random random;
    private final MapPoint[][] map;
    private final ArrayList<City> cities = new ArrayList<>();
    private final ArrayList<ArmyShop> armyShops = new ArrayList<>();
    private final ArrayList<Castle> castles = new ArrayList<>();
    private Spell spell;

    public EntityGenerator(Glade country) {
        this.random = new Random();
        this.map = country.getMapPoints();
    }

    public static void reset() {
        cityNamesMask = new byte[28];
        castleNamesMask = new byte[28];
    }

    public void citiesAndCastles() {
        updateNamesMask(castleNamesMask);
        castles();

        updateNamesMask(cityNamesMask);
        cities();
    }

    private void cities() {
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
                cities.add(createCity(map[x][y], castles.get(count)));
                count++;
            }
        }
    }

    private City createCity(MapPoint mp, Castle castle) {
        int nameId = 0;
        for (int i = 0; i < cityNamesMask.length; i++) {
            if (cityNamesMask[i] == 1) {
                nameId = i + 1;
                cityNamesMask[i] = -1;
                break;
            }
        }
        return new City(mp, nameId, castle);
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

    private void castles() {
        int count = 0;
        while (count < 5) {
            Castle castle = tryPlaceCastle(random.nextInt(Country.MAX_MAP_SIZE),
                    random.nextInt(Country.MAX_MAP_SIZE));
            if (castle != null) {
                castle.generateArmy(1000, 0);
                count++;
                castles.add(castle);
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
            int nameId = 0;
            for (int i = 0; i < castleNamesMask.length; i++) {
                if (castleNamesMask[i] == 1) {
                    nameId = i + 1;
                    castleNamesMask[i] = -1;
                    break;
                }
            }
            Castle castle = new Castle(map[x][y], nameId);
            new CastleRight(map[x + 1][y]);
            new CastleLeft(map[x - 1][y]);
            tryPlaceCaptain(x, y + 1, 1000, 2000);
            return castle;
        }
        return null;
    }


    public void captains(int minAuthority, int maxAuthority) {
        int count = 0;
        while (count < 15) {
            if (tryPlaceCaptain(random.nextInt(Country.MAX_MAP_SIZE),
                    random.nextInt(Country.MAX_MAP_SIZE), minAuthority, maxAuthority))
                count++;
        }
    }

    private boolean tryPlaceCaptain(int x, int y, int minAuthority, int maxAuthority) {
        if (map[x][y].getLand() == R.drawable.land && map[x][y].getEntity() == null) {
            Fighting captain = new Captain(map[x][y]);
            int authority = minAuthority + random.nextInt(maxAuthority - minAuthority);
            captain.generateArmy(authority, 0);
            return true;
        }
        return false;
    }

    public void armies(int count, int... groups) {
        int run = 0;
        while (run < count) {
            MapPoint mp = map[random.nextInt(65)][random.nextInt(65)];
            if (mp.getEntity() == null && mp.getLand() == R.drawable.land) {
                armyShops.add(createArmy(mp, groups));
                run++;
            }
        }
    }

    private ArmyShop createArmy(MapPoint mp, int... groups) {
        return new ArmyShop(mp, groups);
    }

    public void mapNext() {
        new MapNext(getEmptyLand());
    }

    public Metro metro() {
        return new Metro(getEmptyLand());
    }

    private MapPoint getEmptyLand() {
        int x;
        int y;
        do {
            y = random.nextInt(54) + 5;
            x = random.nextInt(54) + 5;
        } while (map[x][y].getLand() != R.drawable.land && map[x][y].getEntity() == null);
        return map[x][y];
    }

    public void updateSpell() {
        if (spell != null) {
            spell.destroy();
            spell = null;
        }
        if (Math.random() < 0.1) {
            spell = new Spell(getEmptyLand());
        }
    }

    private void updateNamesMask(byte[] namesMask) {
        int count0 = 0;
        for (int i = 0; i < namesMask.length; i++) {
            if (namesMask[i] == 1) {
                namesMask[i] = -1;
            }
            if (namesMask[i] == 0) {
                count0++;
            }
        }
        if (count0 < 5) {
            throw new NullPointerException();
        }
        Random rand = new Random();
        int i = 1;
        while (i < 5) {
            int n = rand.nextInt(namesMask.length);
            if (namesMask[n] == 0) {
                namesMask[n] = 1;
                i++;
            }
        }
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
    public Iterator<ArmyShop> getArmyShops() {
        ArrayList<Iterator<ArmyShop>> iterators = new ArrayList<>();
        for (ArmyShopsOwner armyShop : armyShops) {
            iterators.add(armyShop.getArmyShops());
        }
        return new EntityIterator<>(iterators);
    }
}
