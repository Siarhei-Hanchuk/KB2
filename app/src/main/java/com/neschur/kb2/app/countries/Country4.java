package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

class Country4 extends Country {

    public Country4(boolean hard, byte[] cityNamesMask) {
        super(cityNamesMask);
        this.id = 3;
        river(40);
        river(30);
        cities();
        if (hard) {
            landscape(0.7, R.drawable.stone);
        } else {
            landscape(0.5, R.drawable.stone);
        }
        cities();
        castles();
        guidePosts();
        goldChests(40, getId());
        army(5, 4);
        mapNext();
    }
}
