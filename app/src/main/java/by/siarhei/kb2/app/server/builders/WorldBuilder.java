package by.siarhei.kb2.app.server.builders;

import by.siarhei.kb2.app.server.countries.Country;
import by.siarhei.kb2.app.server.countries.World;
import by.siarhei.kb2.app.server.models.Game;

class WorldBuilder {
    private static final int[] defaultWorldSchema = {
            CountryBuilder.COUNTRY_ONE,
            CountryBuilder.COUNTRY_TWO,
            CountryBuilder.COUNTRY_THREE,
            CountryBuilder.COUNTRY_FOUR,
            CountryBuilder.COUNTRY_FIVE
    };
    private static final int[] testWorldSchema = {
            CountryBuilder.COUNTRY_TEST,
            CountryBuilder.COUNTRY_TWO,
            CountryBuilder.COUNTRY_THREE,
            CountryBuilder.COUNTRY_FOUR,
            CountryBuilder.COUNTRY_FIVE
    };

    static World build(int mode) {
        Country[] countries;
        switch (mode) {
            case Game.MODE_TEST:
                countries = testWorld();
                break;
            case Game.MODE_GAME:
                countries = hardWorld();
                break;
            default:
                countries = defaultWorld();
                break;
        }

        return new World(countries);
    }

    private static Country[] testWorld() {
        return buildWorld(5, testWorldSchema, false);
    }

    private static Country[] hardWorld() {
        return buildWorld(5, defaultWorldSchema, true);
    }

    private static Country[] defaultWorld() {
        return buildWorld(5, defaultWorldSchema, false);
    }

    private static Country[] buildWorld(int size, int[] ccc, boolean hardMode) {
        Country[] countries = new Country[size];
        for (int i = 0; i < size; i++) {
            CountryBuilder countryBuilder = new CountryBuilder();
            countries[i] = countryBuilder.build(ccc[i], hardMode);
        }
        return countries;
    }
}
