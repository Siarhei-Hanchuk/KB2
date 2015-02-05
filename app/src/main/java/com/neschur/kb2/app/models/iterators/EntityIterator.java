package com.neschur.kb2.app.models.iterators;

import java.util.ArrayList;
import java.util.Iterator;

public class EntityIterator<T> implements Iterator<T> {
    private ArrayList<Iterator<T>> iterators;
    private int currentIterator = 0;

    public EntityIterator(ArrayList<Iterator<T>> iterators) {
        this.iterators = iterators;
    }

    @Override
    public T next() {
        T dst = null;
        while (iterators.size() > currentIterator && dst == null) {
            while (!iterators.get(currentIterator).hasNext()) {
                currentIterator++;
            }
            dst = iterators.get(currentIterator).next();
        }
        return dst;
    }

    @Override
    public boolean hasNext() {
        for (int i = currentIterator; i < iterators.size(); i++) {
            if (iterators.get(i).hasNext())
                return true;
        }
        return false;
    }

    @Override
    public void remove() {
    }
}
