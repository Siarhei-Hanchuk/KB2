package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

class Country1 extends Country {

    public Country1(byte[] cityNamesMask) {
        super(cityNamesMask);
        this.id = 0;
        river(30);
        river(20);
        river(40);
        landscape(7, R.drawable.forest);
        landscape(20, R.drawable.stone);

        cities();
        guidePosts();
        goldChests(40, getId());
        army(5, 0);
        army(5, 1);
        mapNext();
    }
}
