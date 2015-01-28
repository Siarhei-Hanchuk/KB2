package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.models.Glade;
import com.neschur.kb2.app.warriors.Warrior;
import com.neschur.kb2.app.warriors.WarriorSquad;

public class WarriorEntity extends Entity {
    private Warrior warrior;
    private boolean friendly;
    private int step;
    private int count;

    public WarriorEntity(Glade glade, int x, int y, Warrior warrior, int count, boolean friendly) {
        super(glade, x, y);
        this.warrior = warrior;
        this.count = count;
        this.friendly = true;
        if (warrior.isFly())
            this.step = 6;
        else
            this.step = warrior.getStep();
    }

    @Override
    public int getID() {
        return warrior.getId();
    }

    public boolean isFriendly() {
        return friendly;
    }

    public Warrior getWarrior() {
        return warrior;
    }

    public int getStep() {
        return step;
    }

    public void reduceStep(int step) {
        if (!warrior.isFly())
            this.step -= step;
    }
}
