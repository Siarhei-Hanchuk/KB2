package by.siarhei.kb2.app.server.models.iterators;

import by.siarhei.kb2.app.server.entities.City;

import java.util.Iterator;

public interface CitiesOwner {
    Iterator<City> getCities();
}
