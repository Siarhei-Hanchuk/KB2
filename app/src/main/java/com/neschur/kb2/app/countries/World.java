package com.neschur.kb2.app.countries;

public class World {
    private Country country[] = new Country[5];

    public World() {
        country[0] = new Country1(0);
        country[1] = new Country2(1);
        country[2] = new Country3(2);
        country[3] = new Country4(3);
        country[4] = new Country5(4);
    }

    public Country getCountry(int i) {
        return country[i];
    }
}

