package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

class Country3 extends Country {

    public Country3(boolean hard, byte[] cityNamesMask) {
        super(cityNamesMask);
        this.id = 2;
        river(40);
        river(30);
        cities();
        if (hard) {
            landscape(0.7, R.drawable.forest);
        } else {
            landscape(0.5, R.drawable.forest);
        }
        goldChests(40, getId());
        army(5, 3);
    }
}
