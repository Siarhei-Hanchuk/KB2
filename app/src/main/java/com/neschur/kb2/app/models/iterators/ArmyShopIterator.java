package com.neschur.kb2.app.models.iterators;

import com.neschur.kb2.app.countries.ArmyShops;
import com.neschur.kb2.app.entities.ArmyShop;

import java.util.ArrayList;

public class ArmyShopIterator {
    private ArrayList<ArmyShopIterator> iterators;
    private int currentIterator = 0;
    private int armyId = -1;

    public ArmyShopIterator(ArrayList<ArmyShopIterator> iterators) {
        this.iterators = iterators;
    }

    public void init() {
        init(armyId);
    }

    public void init(int armyId) {
        this.armyId = armyId;
        currentIterator = 0;
        for(ArmyShopIterator iterator: iterators) {
            iterator.init(armyId);
        }
    }

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

    public boolean isDone() {
        for (int i = currentIterator; i < iterators.size(); i++) {
            if(!iterators.get(i).isDone())
                return false;
        }
        return true;
    }
}

