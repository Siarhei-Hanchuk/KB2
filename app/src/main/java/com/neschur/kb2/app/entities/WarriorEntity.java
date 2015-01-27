package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.models.Glade;
import com.neschur.kb2.app.warriors.Warrior;
import com.neschur.kb2.app.warriors.WarriorSquad;

public class WarriorEntity extends Entity {
    private WarriorSquad warrior;
    private boolean friendly;
    private int step;

    public WarriorEntity(Glade glade, int x, int y, WarriorSquad warrior, boolean friendly) {
        super(glade, x, y);
        this.warrior = warrior;
        this.friendly = true;
        this.step = warrior.getWarrior().getStep();
    }

    @Override
    public int getID() {
        return warrior.getWarrior().getId();
    }

    public boolean isFriendly() {
        return friendly;
    }

    public Warrior getWarrior() {
        return warrior.getWarrior();
    }

    public int getStep() {
        return step;
    }

    public void reduceStep(int step) {
        this.step -= step;
    }
}
