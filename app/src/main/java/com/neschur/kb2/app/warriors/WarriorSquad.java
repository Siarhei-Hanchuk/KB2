package com.neschur.kb2.app.warriors;

import java.io.Serializable;

public class WarriorSquad implements Serializable {
    private final Warrior warrior;
    private int count;

    public WarriorSquad(Warrior warrior, int count) {
        this.warrior = warrior;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void changeCount(int delta) {
        count += delta;
    }

    public Warrior getWarrior() {
        return warrior;
    }
}
