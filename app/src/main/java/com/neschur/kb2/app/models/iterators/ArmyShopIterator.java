package com.neschur.kb2.app.models.iterators;

import com.neschur.kb2.app.entities.ArmyShop;

import java.util.ArrayList;
import java.util.Iterator;

public class ArmyShopIterator implements Iterator<ArmyShop> {
    private ArrayList<Iterator<ArmyShop>> iterators;
    private int currentIterator = 0;

    public ArmyShopIterator(ArrayList<Iterator<ArmyShop>> iterators) {
        this.iterators = iterators;
    }

    @Override
    public ArmyShop next() {
        ArmyShops dst = null;
        while (iterators.size() > currentIterator && dst == null) {
            while(!iterators.get(currentIterator).hasNext()) {
                currentIterator++;
            }
            dst = iterators.get(currentIterator).next();
        }
        return (ArmyShop)dst;
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
    public void remove() {}
}

