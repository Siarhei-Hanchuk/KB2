package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.City;

public class Country1 extends Country {

    public Country1(final int id) {
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

        new ArmyShop(this, 5, 8, 0);
        new City(this, 6, 5);
        createCaptain(8, 5);
    }
}
