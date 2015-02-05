package com.neschur.kb2.app.models.iterators;

import com.neschur.kb2.app.entities.City;

import java.util.ArrayList;
import java.util.Iterator;

public class CitiesIterator implements Iterator<City> {
    private ArrayList<Iterator<City>> iterators;
    private int currentIterator = 0;

    public CitiesIterator(ArrayList<Iterator<City>> iterators) {
        this.iterators = iterators;
    }

    @Override
    public City next() {
        Cities dst = null;
        while (iterators.size() > currentIterator && dst == null) {
            while(!iterators.get(currentIterator).hasNext()) {
                currentIterator++;
            }
            dst = iterators.get(currentIterator).next();
        }
        return (City)dst;
    }

    @Override
    public boolean hasNext() {
        for (int i = currentIterator; i < iterators.size(); i++) {
            if(iterators.get(i).hasNext())
                return true;
        }
        return false;
    }


    @Override
    public void remove() {

    }
}
