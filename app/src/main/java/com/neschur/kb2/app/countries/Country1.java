package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.models.iterators.EntityIterator;

import java.util.ArrayList;
import java.util.Iterator;

class Country1 extends Country {

    public Country1(boolean hard, byte[] cityNamesMask) {
        super(cityNamesMask);
        this.id = 0;
        baseGenerator.river(30);
        baseGenerator.river(20);
        baseGenerator.river(40);

        cities = entityGenerator.cities();
        entityGenerator.castles();
        entityGenerator.guidePosts();
        entityGenerator.goldChests(40, getId());

        ArrayList<Iterator<ArmyShop>> iterators = new ArrayList<>();
        iterators.add(entityGenerator.armies(5, 0));
        iterators.add(entityGenerator.armies(5, 1));
        armyShops = new EntityIterator(iterators);

        entityGenerator.mapNext();
        entityGenerator.captains();

        if (hard) {
            baseGenerator.landscape(0.33, R.drawable.forest);
            baseGenerator.landscape(0.33, R.drawable.stone);
        } else {
            baseGenerator.landscape(0.14, R.drawable.forest);
            baseGenerator.landscape(0.05, R.drawable.stone);
        }
    }
}
