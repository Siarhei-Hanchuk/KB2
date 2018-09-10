package by.siarhei.kb2.app.entities;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.models.MapPoint;
import by.siarhei.kb2.app.models.iterators.ArmyShopsOwner;
import by.siarhei.kb2.app.warriors.Warrior;
import by.siarhei.kb2.app.warriors.WarriorFactory;

import java.util.Iterator;

public class ArmyShop extends EntityImpl implements ArmyShopsOwner {
    private final Warrior warrior;
    private int count;

    public ArmyShop(MapPoint point, int... groups) {
        super();
        warrior = WarriorFactory.createRandomFromGroup(groups);
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
            public void remove() {
            }
        };
    }

}
