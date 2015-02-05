package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.iterators.CitiesOwner;

import java.util.Iterator;
import java.util.Random;

public class City extends EntityImpl implements CitiesOwner {
    private final int nameId;
    private int[] workers;

    public City(MapPoint point, int nameId) {
        super(point);
        this.nameId = nameId;
        resetWorkers();
    }

    public void resetWorkers() {
        Random rand = new Random();
        int[] _workers = {rand.nextInt(8), rand.nextInt(8), rand.nextInt(8), rand.nextInt(8)};
        workers = _workers;
    }

    @Override
    public int getID() {
        return R.drawable.city;
    }

    public int getWorkers(int id) {
        return workers[id];
    }

    public void changeWorkers(int id, int count) {
        workers[id] += count;
    }

    public int getNameId() {
        return nameId;
    }

    @Override
    public Iterator<City> getCities() {
        final City self = this;

        return new Iterator<City>() {
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public City next() {
                hasNext = false;
                return self;
            }

            @Override
            public void remove() {}
        };

    }
}
