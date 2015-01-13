package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

public class Country4 extends Country {

    protected int goldChestMax() {
        return 2660;
    }

    protected int goldChestMin() {
        return 930;
    }

    public Country4() {
        river(40);
        river(30);
        //cities();
        //castels(map,gw_c1_capitan);
        landscape(2, R.drawable.stone);
        //goldChests();
    }
}
