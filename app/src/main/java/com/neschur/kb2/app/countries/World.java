package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.models.iterators.ArmyShopsOwner;
import com.neschur.kb2.app.models.iterators.CitiesOwner;
import com.neschur.kb2.app.models.iterators.EntityIterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class World implements Serializable, ArmyShopsOwner, CitiesOwner {
    private Country[] country;
    private byte[] cityNames = new byte[25];

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
        country[0] = new CountryTraining(updateCityNamesMask());
        country[1] = new Country2(updateCityNamesMask());
        country[2] = new Country3(updateCityNamesMask());
        country[3] = new Country4(updateCityNamesMask());
        country[4] = new Country5(updateCityNamesMask());
    }

    private void defaultWorld() {
        country = new Country[5];
        country[0] = new Country1(updateCityNamesMask());
        country[1] = new Country2(updateCityNamesMask());
        country[2] = new Country3(updateCityNamesMask());
        country[3] = new Country4(updateCityNamesMask());
        country[4] = new Country5(updateCityNamesMask());
    }

    private byte[] updateCityNamesMask() {
        for (int i = 0; i < cityNames.length; i++) {
            if(cityNames[i] == 1) {
                cityNames[i] = -1;
            }
        }
        Random rand = new Random();
        for (int i = 0; i < 5;) {
            int n = rand.nextInt(cityNames.length);
            if(cityNames[n] == 0) {
                cityNames[n] = 1;
                i++;
            }
        }
        return cityNames;
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

