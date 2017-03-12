package by.siarhei.kb2.app.controllers;

import by.siarhei.kb2.app.entities.City;
import by.siarhei.kb2.app.entities.Entity;

public interface GameCallback {
    void activateEntity(Entity entity);

    void weekFinish(String armyTextId, City city);
}
