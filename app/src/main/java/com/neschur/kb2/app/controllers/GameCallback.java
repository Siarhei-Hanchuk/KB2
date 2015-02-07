package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;

public interface GameCallback {
    public void activateEntity(Entity entity);

    public void weekFinish(String armyTextId, City city);
}
