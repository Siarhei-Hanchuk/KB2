package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

class Country4 extends Country {

    public Country4() {
        this.id = 3;
        river(40);
        river(30);
        cities();
        landscape(2, R.drawable.stone);
        goldChests(40, getId());
    }
}
