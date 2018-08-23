package by.siarhei.kb2.app.builder;

import by.siarhei.kb2.app.countries.Country;
import by.siarhei.kb2.app.countries.Country1;
import by.siarhei.kb2.app.countries.Country2;
import by.siarhei.kb2.app.countries.Country3;
import by.siarhei.kb2.app.countries.Country4;
import by.siarhei.kb2.app.countries.Country5;
import by.siarhei.kb2.app.countries.CountryTest;
import by.siarhei.kb2.app.countries.World;
import by.siarhei.kb2.app.countries.generators.EntityGenerator;
import by.siarhei.kb2.app.models.Game;

public class WorldBuilder {
    static World build(int mode) {
        EntityGenerator.reset();
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
        Country[] countries = new Country[5];
        countries[0] = new CountryTest();
        countries[1] = new Country2(true);
        countries[2] = new Country3(true);
        countries[3] = new Country4(true);
        countries[4] = new Country5(true);
        return countries;
    }

    private static Country[] hardWorld() {
        Country[] countries = new Country[5];
        countries[0] = new Country1(true);
        countries[1] = new Country2(true);
        countries[2] = new Country3(true);
        countries[3] = new Country4(true);
        countries[4] = new Country5(true);
        return countries;
    }

    private static Country[] defaultWorld() {
        Country[] countries = new Country[5];
        countries[0] = new Country1(false);
        countries[1] = new Country2(false);
        countries[2] = new Country3(false);
        countries[3] = new Country4(false);
        countries[4] = new Country5(false);
        return countries;
    }
}
