package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

class Country2 extends Country {

    public Country2() {
        this.id = 1;
        river(40);
        river(50);
        river(30);
        landscape(7, R.drawable.water);
        landscape(12, R.drawable.forest);
        landscape(31, R.drawable.stone);
        cities();
        army(5, 2);
        goldChests(40, getId());
    }
}
