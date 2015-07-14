package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.countries.generators.EntityGenerator;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.models.Game;
import com.neschur.kb2.app.models.iterators.ArmyShopsOwner;
import com.neschur.kb2.app.models.iterators.CitiesOwner;
import com.neschur.kb2.app.models.iterators.EntityIterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class World implements Serializable, ArmyShopsOwner, CitiesOwner {
    private Country[] country;

    public World(int mode) {
        EntityGenerator.reset();
        switch (mode) {
            case Game.MODE_TEST:
                testWorld();
                break;
            case Game.MODE_GAME:
                hardWorld();
                break;
            case Game.MODE_TRAINING:
                defaultWorld();
                break;
        }
    }

    public Country getCountry(int i) {
        return country[i];
    }

    private void testWorld() {
        country = new Country[5];
        country[0] = new CountryTest();
        country[1] = new Country2(true);
        country[2] = new Country3(true);
        country[3] = new Country4(true);
        country[4] = new Country5(true);
    }

    private void hardWorld() {
        country = new Country[5];
        country[0] = new Country1(true);
        country[1] = new Country2(true);
        country[2] = new Country3(true);
        country[3] = new Country4(true);
        country[4] = new Country5(true);
    }

    private void defaultWorld() {
        country = new Country[5];
        country[0] = new Country1(false);
        country[1] = new Country2(false);
        country[2] = new Country3(false);
        country[3] = new Country4(false);
        country[4] = new Country5(false);
    }

    @Override
    public Iterator<ArmyShop> getArmyShops() {
        ArrayList<Iterator<ArmyShop>> iterators = new ArrayList<>();
        for (ArmyShopsOwner shop : country) {
            iterators.add(shop.getArmyShops());
        }
        return new EntityIterator<>(iterators);
    }

    @Override
    public Iterator<City> getCities() {
        ArrayList<Iterator<City>> iterators = new ArrayList<>();
        for (CitiesOwner city : country) {
            iterators.add(city.getCities());
        }
        return new EntityIterator<>(iterators);
    }

    public void activateCaptains() {
        for (int i = 0; i < country.length; i++) {
            country[i].activateCaptains();
        }
    }
}

