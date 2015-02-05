package com.neschur.kb2.app.models.iterators;

import com.neschur.kb2.app.entities.ArmyShop;

public class NullArmyShopIterator implements Iterator<ArmyShop> {
    private ArmyShop armyShop;
    private boolean isDone = false;

    public NullArmyShopIterator(ArmyShop armyShop) {
        this.armyShop = armyShop;
    }

    @Override
    public void init(int armyId) {
        if(armyId > -1 && armyShop.getWarrior().getId() != armyId)
            isDone = true;
    }

    @Override
    public ArmyShop next() {
        if(!isDone) {
            isDone = true;
            return armyShop;
        }
        return null;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }
}
