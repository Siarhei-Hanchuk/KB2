package com.neschur.kb2.app.models.iterators;

import com.neschur.kb2.app.entities.ArmyShop;

public class NullArmyShopIterator extends ArmyShopIterator {
    private ArmyShop armyShop;
    private boolean isDone = false;
    private int armyId = -1;

    public NullArmyShopIterator(ArmyShop armyShop) {
        super(null);
        this.armyShop = armyShop;
    }

    public void init() {
        init(armyId);
    }

    @Override
    public void init(int armyId) {
        this.armyId = armyId;
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
