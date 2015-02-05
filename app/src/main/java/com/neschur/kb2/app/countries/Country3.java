package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

class Country3 extends Country {

    public Country3(boolean hard, byte[] cityNamesMask) {
        super(cityNamesMask);
        this.id = 2;
        baseGenerator.river(40);
        baseGenerator.river(30);
        if (hard) {
            baseGenerator.landscape(0.7, R.drawable.forest);
        } else {
            baseGenerator.landscape(0.5, R.drawable.forest);
        }

        entityGenerator.cities();
        entityGenerator.castles();
        entityGenerator.guidePosts();
        entityGenerator.goldChests(40, getId());
        entityGenerator.armies(5, 3);
        entityGenerator.mapNext();
    }
}
