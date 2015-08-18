package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Captain;
import com.neschur.kb2.app.entities.Castle;
import com.neschur.kb2.app.entities.CastleLeft;
import com.neschur.kb2.app.entities.CastleRight;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.GoldChest;

class CountryTest extends Country {
    public CountryTest() {
        super();
        this.id = 0;

        new ArmyShop(getMapPoint(5, 8), 0);
        new ArmyShop(getMapPoint(5, 9), 0);
        new ArmyShop(getMapPoint(5, 10), 0);

        Captain captain = new Captain(getMapPoint(8, 5));
        captain.generateArmy(28, 0);

        Castle castle = new Castle(getMapPoint(8, 8), 0);
        new CastleRight(getMapPoint(9, 8));
        new CastleLeft(getMapPoint(7, 8));
        castle.generateArmy(50, 0);

        new GoldChest(getMapPoint(5, 6), 1);

        new City(getMapPoint(6, 5), 0, castle);
    }
}
