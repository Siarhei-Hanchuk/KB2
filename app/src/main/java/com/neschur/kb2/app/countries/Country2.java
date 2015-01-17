package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

public class Country2 extends Country {

    protected int goldChestMax() {
        return 480;
    }

    protected int goldChestMin() {
        return 1120;
    }

    public Country2(int id) {
        super(id);
        river(40);
        river(50);
        river(30);
        landscape(7, R.drawable.water);
        landscape(12, R.drawable.forest);
        landscape(31, R.drawable.stone);
        cities();
        goldChests(40);
    }

}
