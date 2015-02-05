package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.models.iterators.ArmyShopsOwner;
import com.neschur.kb2.app.models.iterators.CitiesOwner;
import com.neschur.kb2.app.models.iterators.EntityIterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class World implements Serializable, ArmyShopsOwner, CitiesOwner {
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
        for(ArmyShopsOwner shop: country) {
            iterators.add(shop.getArmyShops());
        }
        return new EntityIterator(iterators);
    }

    @Override
    public Iterator<City> getCities() {
        ArrayList<Iterator<City>> iterators = new ArrayList<>();
        for(CitiesOwner city: country) {
            iterators.add(city.getCities());
        }
        return new EntityIterator(iterators);
    }
}

