package com.neschur.kb2.app.countries;

class CountryTraining extends Country {
    public CountryTraining(byte[] cityNamesMask) {
        super(cityNamesMask);
        this.id = 0;

        createArmy(getMapPoint(5, 8), 0);
        createArmy(getMapPoint(5, 9), 0);
        createArmy(getMapPoint(5, 10), 0);
        createCity(getMapPoint(6, 5));
        createCaptain(8, 5);
    }
}
