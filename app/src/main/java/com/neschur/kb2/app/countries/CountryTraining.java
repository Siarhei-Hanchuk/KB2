package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.City;

/**
 * Created by siarhei on 25.01.15.
 */
public class CountryTraining extends Country{
    public CountryTraining() {
        this.id = 0;

        new ArmyShop(this, 5, 8, 0);
        new City(this, 6, 5);
        createCaptain(8, 5);
    }
}
