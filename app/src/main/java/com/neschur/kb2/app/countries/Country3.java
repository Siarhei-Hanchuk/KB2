package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

class Country3 extends Country {

    public Country3(byte[] cityNamesMask) {
        super(cityNamesMask);
        this.id = 2;
        river(40);
        river(30);
        cities();
        landscape(2, R.drawable.forest);
        goldChests(40, getId());
        army(5, 3);
    }
}
