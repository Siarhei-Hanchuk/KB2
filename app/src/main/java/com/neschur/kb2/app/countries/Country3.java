package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

public class Country3 extends Country {

    protected int goldChestMax() {
        return 1970;
    }

    protected int goldChestMin() {
        return 690;
    }

    public Country3() {
        river(40);
        river(30);
        //cities();
        //castels(map,gw_c1_capitan);
        landscape(2, R.drawable.forest);
        //goldChests();
    }
}
