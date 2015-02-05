package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.models.iterators.ArmyShops;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.warriors.Warrior;
import com.neschur.kb2.app.warriors.WarriorFactory;

import java.util.Iterator;

public class ArmyShop extends EntityImpl implements ArmyShops {
    private final Warrior warrior;
    private int count;

    public ArmyShop(MapPoint point, int group) {
        super(point);
        warrior = WarriorFactory.createRandomFromGroup(group);
        resetCount();
    }

    @Override
    public int getID() {
        return R.drawable.army_shop;
    }

    public Warrior getWarrior() {
        return warrior;
    }

    public int getCount() {
        return count;
    }

    public boolean pullArmy(int count) {
        if (this.count - count >= 0) {
            this.count -= count;
            return true;
        }
        return false;
    }

    public void resetCount() {
        this.count = warrior.getCountInShop();
    }

    @Override
    public Iterator<ArmyShop> getArmyShops() {
        final ArmyShop self = this;
        return new Iterator<ArmyShop>() {
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public ArmyShop next() {
                hasNext = false;
                return self;
            }

            @Override
            public void remove() {}
        };
    }

}
