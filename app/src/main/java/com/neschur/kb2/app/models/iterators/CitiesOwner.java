package com.neschur.kb2.app.models.iterators;

import com.neschur.kb2.app.entities.City;

import java.util.Iterator;

public interface CitiesOwner {
    Iterator<City> getCities();
}
