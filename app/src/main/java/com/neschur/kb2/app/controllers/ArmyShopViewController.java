package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.entities.ArmyShop;

public interface ArmyShopViewController extends ViewController, PlayerViewsController {
    void buyArmy(ArmyShop shop, int count);
}
