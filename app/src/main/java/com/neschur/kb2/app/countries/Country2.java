package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

class Country2 extends Country {

    public Country2(boolean hard, byte[] cityNamesMask) {
        super(cityNamesMask);
        this.id = 1;
        river(40);
        river(50);
        river(30);
        if (hard) {
            landscape(0.125, R.drawable.water);
            landscape(0.125, R.drawable.forest);
            landscape(0.05, R.drawable.stone);
        } else {
            landscape(0.14, R.drawable.water);
            landscape(0.08, R.drawable.forest);
            landscape(0.03, R.drawable.stone);
        }

        cities();
        castles();
        guidePosts();
        goldChests(40, getId());
        army(5, 2);
        mapNext();
    }
}
