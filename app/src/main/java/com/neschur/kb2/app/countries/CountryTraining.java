package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.City;

class CountryTraining extends Country {
    public CountryTraining() {
        this.id = 0;

        new ArmyShop(getMapPoint(5, 8), 0);
        new ArmyShop(getMapPoint(5, 9), 0);
        new ArmyShop(getMapPoint(5, 10), 0);
        new City(getMapPoint(6, 5));
        createCaptain(8, 5);

    }
}
