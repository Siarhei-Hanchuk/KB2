package com.neschur.kb2.app.models.iterators;

import com.neschur.kb2.app.entities.ArmyShop;

import java.util.ArrayList;

public class ArmyShopIterator implements Iterator<ArmyShop> {
    private ArrayList<Iterator<ArmyShop>> iterators;
    private int currentIterator = 0;

    public ArmyShopIterator(ArrayList<Iterator<ArmyShop>> iterators) {
        this.iterators = iterators;
    }

    @Override
    public void init(int armyId) {
        currentIterator = 0;
        for(Iterator iterator: iterators) {
            iterator.init(armyId);
        }
    }

    @Override
    public ArmyShop next() {
        ArmyShops dst = null;
        while (iterators.size() > currentIterator && dst == null) {
            while(iterators.get(currentIterator).isDone()) {
                currentIterator++;
            }
            dst = iterators.get(currentIterator).next();
        }
        return (ArmyShop)dst;
    }

    @Override
    public boolean isDone() {
        for (int i = currentIterator; i < iterators.size(); i++) {
            if(!iterators.get(i).isDone())
                return false;
        }
        return true;
    }
}

