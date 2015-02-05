package com.neschur.kb2.app.models.iterators2;

import com.neschur.kb2.app.entities.City;

import java.util.Iterator;

public class NullCityIterator implements Iterator<City> {
    private City object;
    private boolean isDone = false;

    public NullCityIterator(City city) {
        this.object = city;
    }

    @Override
    public boolean hasNext() {
        return !isDone;
    }

    @Override
    public City next() {
        if(!isDone) {
            isDone = true;
            return object;
        }
        return null;
    }

    @Override
    public void remove() {

    }
}
