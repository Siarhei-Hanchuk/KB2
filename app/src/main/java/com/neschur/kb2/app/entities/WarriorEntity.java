package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.models.Glade;
import com.neschur.kb2.app.warriors.Warrior;
import com.neschur.kb2.app.warriors.WarriorSquad;

public class WarriorEntity extends Entity {
    private WarriorSquad warrior;
    private boolean friendly;

    public WarriorEntity(Glade glade, int x, int y, WarriorSquad warrior, boolean friendly) {
        super(glade, x, y);
        this.warrior = warrior;
        this.friendly = true;
    }

    @Override
    public int getID() {
        return warrior.getWarrior().getId();
    }

    public boolean isFriendly() {
        return friendly;
    }
}
