package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

public class Country1 extends Country {

    public Country1(int id) {
        super(id);
        river(30);
        river(20);
        river(40);
        landscape(7, R.drawable.forest);
        landscape(20, R.drawable.stone);

        cities();
        guidePosts();
        goldChests(40, 220, 550);
        army(10, 0);
        mapNext();
    }
}
