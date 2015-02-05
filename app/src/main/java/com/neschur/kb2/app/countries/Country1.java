package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

class Country1 extends Country {

    public Country1(boolean hard, byte[] cityNamesMask) {
        super(cityNamesMask);
        this.id = 0;
        river(30);
        river(20);
        river(40);
        if (hard) {
            landscape(0.33, R.drawable.forest);
            landscape(0.33, R.drawable.stone);
        } else {
            landscape(0.14, R.drawable.forest);
            landscape(0.05, R.drawable.stone);
        }

        cities();
        castles();
        guidePosts();
        goldChests(40, getId());
        army(5, 0);
        army(5, 1);
        mapNext();
    }
}
