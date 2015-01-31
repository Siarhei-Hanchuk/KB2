package com.neschur.kb2.app.models.battle;

import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.EntityImpl;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.warriors.Warrior;

public class WarriorEntity implements Entity {
    private MapPoint point;
    private Warrior warrior;
    private boolean friendly;
    private int step;
    private int count;
    private int defence;

    public WarriorEntity(MapPoint point, Warrior warrior, int count, boolean friendly) {
        this.point = point;
        this.warrior = warrior;
        this.count = count;
        this.friendly = friendly;
        this.defence = warrior.getDefence() * count;
        resetStep();
    }

    @Override
    public int getID() {
        return warrior.getId();
    }

    @Override
    public void destroy() {
        point.setEntity(null);
    }

    @Override
    public MapPoint getMapPoint() {
        return point;
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

    public void attack(WarriorEntity warrior) {
        warrior.takeAttack(this.warrior.getDamage() * count);
        step = 0;
    }

    public void takeAttack(int damage) {
        defence -= damage;
        count = defence / warrior.getDefence();
        if (count < 0)
            destroy();
    }

    public int getCount() {
        return count;
    }

    public boolean isShoot() {
        return warrior.isShoot();
    }

    public void resetStep() {
        if (warrior.isFly())
            this.step = 6;
        else
            this.step = warrior.getStep();
    }

    public boolean isFly() {
        return warrior.isFly();
    }
}
