package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;

public interface GameCallback {
    void activateEntity(Entity entity);

    void weekFinish(String armyTextId, City city);
}
