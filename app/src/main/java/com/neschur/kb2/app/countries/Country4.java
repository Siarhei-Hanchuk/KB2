package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

class Country4 extends Country {

    public Country4(boolean hard, byte[] cityNamesMask) {
        super(cityNamesMask);
        this.id = 3;
        baseGenerator.river(40);
        baseGenerator.river(30);

        cities = entityGenerator.cities();
        entityGenerator.castles();
        entityGenerator.guidePosts();
        entityGenerator.goldChests(40, getId());
        armyShops = entityGenerator.armies(5, 4);
        entityGenerator.mapNext();

        if (hard) {
            baseGenerator.landscape(0.7, R.drawable.stone);
        } else {
            baseGenerator.landscape(0.5, R.drawable.stone);
        }
    }
}
