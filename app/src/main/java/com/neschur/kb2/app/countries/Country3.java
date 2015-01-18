package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

public class Country3 extends Country {

    public Country3(int id) {
        super(id);
        river(40);
        river(30);
        cities();
        landscape(2, R.drawable.forest);
        goldChests(40, 690, 1970);
    }
}
