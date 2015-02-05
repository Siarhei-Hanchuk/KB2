package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.models.iterators.ArmyShopIterator;
import com.neschur.kb2.app.models.iterators.ArmyShops;
import com.neschur.kb2.app.models.iterators.Iterator;

import java.io.Serializable;
import java.util.ArrayList;

public class World implements Serializable, ArmyShops {
    private Country[] country;

    public World(int mode) {
        switch (mode) {
            case 0:
                trainingWorld();
                break;
            case 1:
                defaultWorld();
                break;
        }
    }

    public Country getCountry(int i) {
        return country[i];
    }

    private void trainingWorld() {
        country = new Country[5];
        country[0] = new CountryTraining();
        country[1] = new Country2();
        country[2] = new Country3();
        country[3] = new Country4();
        country[4] = new Country5();
    }

    private void defaultWorld() {
        country = new Country[5];
        country[0] = new Country1();
        country[1] = new Country2();
        country[2] = new Country3();
        country[3] = new Country4();
        country[4] = new Country5();
    }

    @Override
    public Iterator<ArmyShop> getArmyShops() {
        ArrayList<Iterator<ArmyShop>> iterators = new ArrayList<>();
        for(ArmyShops shop: country) {
            iterators.add(shop.getArmyShops());
        }
        return new ArmyShopIterator(iterators);
    }
}

