package by.siarhei.kb2.app.server.builders;

import java.util.Random;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.builders.generators.BaseGenerator;
import by.siarhei.kb2.app.server.builders.generators.EntityGenerator;
import by.siarhei.kb2.app.server.countries.Country;
import by.siarhei.kb2.app.server.entities.ArmyShop;
import by.siarhei.kb2.app.server.entities.Captain;
import by.siarhei.kb2.app.server.entities.Castle;
import by.siarhei.kb2.app.server.entities.CastleLeft;
import by.siarhei.kb2.app.server.entities.CastleRight;
import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.entities.GoldenChest;
import by.siarhei.kb2.app.server.entities.GuidePost;
import by.siarhei.kb2.app.server.entities.Metro;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.warriors.WarriorFactory;
import by.siarhei.kb2.app.server.warriors.WarriorSquad;

class CountryBuilder {
    private final static int MAX_MAP_SIZE = 65;
    public final static int COUNTRY_ONE = 1;
    public final static int COUNTRY_TWO = 2;
    public final static int COUNTRY_THREE = 3;
    public final static int COUNTRY_FOUR = 4;
    public final static int COUNTRY_FIVE = 5;
    public final static int COUNTRY_TEST = 99;

    private final static Random random = new Random();
    private final BaseGenerator baseGenerator;
    private final EntityGenerator entityGenerator;
    private final MapPoint[][] map;

    public CountryBuilder() {
        this.map = new MapPoint[MAX_MAP_SIZE][MAX_MAP_SIZE];
        this.baseGenerator = new BaseGenerator(map);
        this.entityGenerator = new EntityGenerator(map);
    }

    public Country build(int id, boolean hardMode) {
        switch (id) {
            case COUNTRY_ONE:
                return build1(hardMode);
            case COUNTRY_TWO:
                return build2(hardMode);
            case COUNTRY_THREE:
                return build3(hardMode);
            case COUNTRY_FOUR:
                return build4(hardMode);
            case COUNTRY_FIVE:
                return build5(hardMode);
            case COUNTRY_TEST:
                return buildTest();
            default:
                return null;
        }
    }

    private Country build1(boolean hardMode) {
        generateBaseField();

        baseGenerator.river(30);
        baseGenerator.river(20);
        baseGenerator.river(40);

        entityGenerator.metro();
        entityGenerator.citiesAndCastles();
        entityGenerator.guidePosts();
        entityGenerator.goldChests(40, 0);
        entityGenerator.armies(10, 1, 0);
        entityGenerator.mapNext();
        entityGenerator.captains(30, 400);

        if (hardMode) {
            baseGenerator.landscape(0.33, R.drawable.forest);
            baseGenerator.landscape(0.33, R.drawable.stone);
        } else {
            baseGenerator.landscape(0.14, R.drawable.forest);
            baseGenerator.landscape(0.05, R.drawable.stone);
        }

        return new Country(random, map, 0);
    }

    private Country build2(boolean hardMode) {
        generateBaseField();

        baseGenerator.river(40);
        baseGenerator.river(50);
        baseGenerator.river(30);
        baseGenerator.river(50);
        baseGenerator.river(50);

        entityGenerator.metro();
        entityGenerator.citiesAndCastles();
        entityGenerator.guidePosts();
        entityGenerator.goldChests(40, 1);
        entityGenerator.armies(5, 2);
        entityGenerator.mapNext();
        entityGenerator.captains(150, 1200);

        if (hardMode) {
            baseGenerator.landscape(0.125, R.drawable.water);
            baseGenerator.landscape(0.125, R.drawable.forest);
            baseGenerator.landscape(0.05, R.drawable.stone);
        } else {
            baseGenerator.landscape(0.14, R.drawable.water);
            baseGenerator.landscape(0.08, R.drawable.forest);
            baseGenerator.landscape(0.03, R.drawable.stone);
        }

        return new Country(random, map, 1);
    }

    private Country build3(boolean hardMode) {
        generateBaseField();

        baseGenerator.river(40);
        baseGenerator.river(30);

        entityGenerator.metro();
        entityGenerator.citiesAndCastles();
        entityGenerator.guidePosts();
        entityGenerator.goldChests(40, 2);
        entityGenerator.armies(5, 3);
        entityGenerator.mapNext();

        if (hardMode) {
            baseGenerator.landscape(0.7, R.drawable.forest);
        } else {
            baseGenerator.landscape( 0.5, R.drawable.forest);
        }

        return new Country(random, map, 2);
    }

    private Country build4(boolean hardMode) {
        generateBaseField();

        baseGenerator.river(40);
        baseGenerator.river(30);

        entityGenerator.metro();
        entityGenerator.citiesAndCastles();
        entityGenerator.guidePosts();
        entityGenerator.goldChests(40, 3);
        entityGenerator.armies(5, 4);
        entityGenerator.mapNext();

        if (hardMode) {
            baseGenerator.landscape(0.7, R.drawable.stone);
        } else {
            baseGenerator.landscape(0.5, R.drawable.stone);
        }


        return new Country(random, map, 3);
    }

    private Country build5(boolean hardMode) {
        generateBaseField();

        sand(map);
        entityGenerator.metro();
        baseGenerator.river(10);
        entityGenerator.citiesAndCastles();
        entityGenerator.guidePosts();
        entityGenerator.goldChests(60, 4);
        entityGenerator.armies(5, 5);

        if (hardMode) {
            stones(map, 0.75);
        } else {
            stones(map, 0.6);
        }

        return new Country(random, map, 4);
    }

    private Country buildTest() {
        generateBaseField();

        map[9][7].setEntity(new Metro());
        map[20][20].setEntity(new Metro());

        map[5][8].setEntity(new ArmyShop(0));
        map[5][9].setEntity(new ArmyShop(0));
        map[5][10].setEntity(new ArmyShop(0));
        map[7][5].setEntity(new GuidePost());

        Captain captain = new Captain();
        map[8][5].setEntity(captain);
//        captain.generateArmy(28, 0);
//        Warrior warriors[] = new Warrior[3];
        captain.warriors[0] = new WarriorSquad(WarriorFactory.create("woodgoblin"), 10);
        captain.warriors[1] = new WarriorSquad(WarriorFactory.create("elf"), 10);
        captain.warriors[2] = new WarriorSquad(WarriorFactory.create("gorilla"), 10);
        captain.authority = 100;

        Castle castle = new Castle(0);
        map[8][8].setEntity((castle));

        map[9][8].setEntity(new CastleRight());
        map[7][8].setEntity(new CastleLeft());
        castle.generateArmy(50, 0);

        map[5][6].setEntity(new GoldenChest(1));

        map[6][5].setEntity(new City(0, castle));

        entityGenerator.mapNext();

        return new Country(random, map, 0);
    }

    private void generateBaseField() {
        for (int i = 0; i < MAX_MAP_SIZE; i++) {
            for (int j = 0; j < MAX_MAP_SIZE; j++) {
                map[i][j] = new MapPoint(i, j);
            }
        }

        baseGenerator.base();
    }

    private static void sand(MapPoint[][] map) {
        for (int i = 6; i < 59; i++) {
            for (int j = 6; j < 11; j++) {
                map[i][j].setLand(R.drawable.sand);
                map[j][i].setLand(R.drawable.sand);
            }
        }
        for (int i = 6; i < 59; i++) {
            for (int j = 54; j < 59; j++) {
                map[j][i].setLand(R.drawable.sand);
                map[i][j].setLand(R.drawable.sand);
            }
        }
    }

    private static void stones(MapPoint[][] map, double frequency) {
        for (int i = 11; i < MAX_MAP_SIZE - 11; i++) {
            for (int j = 11; j < MAX_MAP_SIZE - 11; j++) {
                if (Math.random() < frequency) {
                    if ((map[i][j].getLand() == R.drawable.land) &&
                            (map[i][j].getEntity() == null)) {
                        map[i][j].setLand(R.drawable.stone);
                    }
                }
            }
        }
    }
}

