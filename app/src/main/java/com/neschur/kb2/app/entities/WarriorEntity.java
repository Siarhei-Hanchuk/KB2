package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.models.Glade;
import com.neschur.kb2.app.warriors.Warrior;

public class WarriorEntity extends Entity {
    private Warrior warrior;

    public WarriorEntity(Glade glade, int x, int y, Warrior warrior) {
        super(glade, x, y);
        this.warrior = warrior;
    }

    @Override
    public int getID() {
        return warrior.getId();
    }
}
