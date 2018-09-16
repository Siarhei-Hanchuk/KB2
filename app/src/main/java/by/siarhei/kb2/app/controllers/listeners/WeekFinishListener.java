package by.siarhei.kb2.app.controllers.listeners;

import by.siarhei.kb2.app.server.entities.City;

public interface WeekFinishListener {
    void weekFinish(String armyTextId, City city);
}
