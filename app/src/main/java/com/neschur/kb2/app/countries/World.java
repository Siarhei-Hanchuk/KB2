package com.neschur.kb2.app.countries;

public class World {
    private Country[] country;

    public World() {
        defaultWorld();
    }

    public World(boolean training) {
        if (training) {
            trainingWorld();
        } else {
            defaultWorld();
        }
    }

    public Country getCountry(int i) {
        return country[i];
    }

    private void trainingWorld() {
        country = new Country[1];
        country[0] = new CountryTraining();
    }

    private void defaultWorld() {
        country = new Country[5];
        country[0] = new Country1();
        country[1] = new Country2();
        country[2] = new Country3();
        country[3] = new Country4();
        country[4] = new Country5();
    }
}

