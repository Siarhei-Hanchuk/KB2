package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.ArmyShops;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.iterators.ArmyShopIterator;
import com.neschur.kb2.app.models.iterators.NullArmyShopIterator;
import com.neschur.kb2.app.warriors.Warrior;
import com.neschur.kb2.app.warriors.WarriorFactory;

public class ArmyShop extends EntityImpl implements ArmyShops {
    private final Warrior warrior;
    private int count;

    public ArmyShop(MapPoint point, int group) {
        super(point);
        warrior = WarriorFactory.createRandomFromGroup(group);
        this.count = warrior.getCountInShop();
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

    @Override
    public ArmyShopIterator getArmyShops() {
        return new NullArmyShopIterator(this);
    }
}
