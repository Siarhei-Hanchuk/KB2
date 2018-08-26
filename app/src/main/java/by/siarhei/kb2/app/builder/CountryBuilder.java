package by.siarhei.kb2.app.builder;

import java.util.Random;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.builder.generators.BaseGenerator;
import by.siarhei.kb2.app.builder.generators.EntityGenerator;
import by.siarhei.kb2.app.countries.Country;
import by.siarhei.kb2.app.entities.ArmyShop;
import by.siarhei.kb2.app.entities.Captain;
import by.siarhei.kb2.app.entities.Castle;
import by.siarhei.kb2.app.entities.CastleLeft;
import by.siarhei.kb2.app.entities.CastleRight;
import by.siarhei.kb2.app.entities.City;
import by.siarhei.kb2.app.entities.GoldChest;
import by.siarhei.kb2.app.entities.Metro;
import by.siarhei.kb2.app.models.MapPoint;

public class CountryBuilder {
    public final static int MAX_MAP_SIZE = 65;
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

        Metro metro1 = entityGenerator.metro();
        Metro metro2 = entityGenerator.metro();

        return new Country(random, map, 0);
    }

    private Country build2(boolean hardMode) {
        generateBaseField();

        Metro metro1 = entityGenerator.metro();
        Metro metro2 = entityGenerator.metro();

        baseGenerator.river(40);
        baseGenerator.river(50);
        baseGenerator.river(30);
        baseGenerator.river(50);
        baseGenerator.river(50);

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

        Metro metro1 = entityGenerator.metro();
        Metro metro2 = entityGenerator.metro();

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
        Metro metro1 = entityGenerator.metro();
        Metro metro2 = entityGenerator.metro();

        baseGenerator.river(40);
        baseGenerator.river(30);

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
        Metro metro1 = entityGenerator.metro();
        Metro metro2 = entityGenerator.metro();


        sand(map);
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

    public Country buildTest() {
        generateBaseField();

        Metro metro1 = entityGenerator.metro();
        Metro metro2 = entityGenerator.metro();

        new ArmyShop(map[5][8], 0);
        new ArmyShop(map[5][9], 0);
        new ArmyShop(map[5][10], 0);

        Captain captain = new Captain(map[8][5]);
        captain.generateArmy(28, 0);

        Castle castle = new Castle(map[8][8], 0);
        new CastleRight(map[9][8]);
        new CastleLeft(map[7][8]);
        castle.generateArmy(50, 0);

        new GoldChest(map[5][6], 1);

        new City(map[6][5], 0, castle);

        return new Country(random, map, 0);
    }

    private void generateBaseField() {
        for (int i = 0; i < MAX_MAP_SIZE; i++) {
            for (int j = 0; j < MAX_MAP_SIZE; j++) {
                map[i][j] = new MapPoint(null, i, j);
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

