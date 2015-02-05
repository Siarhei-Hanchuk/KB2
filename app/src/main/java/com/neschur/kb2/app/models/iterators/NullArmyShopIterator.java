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

    public void init(int armyId) {
        this.armyId = armyId;
        System.out.println("init");
//        System.out.println(armyShop.getWarrior().getId() + " " + armyId);
        if(armyId > -1 && armyShop.getWarrior().getId() != armyId)
            isDone = true;
    }

    public ArmyShop next() {
        if(!isDone) {
            isDone = true;
            return armyShop;
        }
        return null;
    }

    public boolean isDone() {
        return isDone;
    }

//
//    public ArmyShop first(int armyId) {
//        isDone = true;
//        if (armyShop.getWarrior().getId() == armyId)
//            return armyShop;
//        return null;
//    }
//
//    public ArmyShop next() {
//        return null;
//    }
//
//    public boolean isDone() {
//        return isDone;
//    }
//
//    public boolean isDone(int armyId) {
//        if (!isDone && armyShop.getWarrior().getId() == armyId)
//            return false;
//        return true;
//    }
}
