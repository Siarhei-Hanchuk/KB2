package com.neschur.kb2.app.models.battle;

import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.Mover;
import com.neschur.kb2.app.warriors.Warrior;

public class WarriorEntity implements Entity, Warrior {
    private final Mover mover;
    private final Warrior warrior;
    private MapPoint point;
    private boolean friendly;
    private int step;
    private int count;
    private int defence;

    public WarriorEntity(MapPoint point, Warrior warrior, int count, boolean friendly) {
        mover = new Mover(point.getGlade());
        point.setEntity(this);
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

    @Override
    public void setMapPoint(MapPoint point) {
        this.point = point;
    }

    @Override
    public String getTextId() {
        return warrior.getTextId();
    }

    @Override
    public int getId() {
        return warrior.getId();
    }

    @Override
    public int getPriceInShop() {
        return -1;
    }

    @Override
    public int getCountInShop() {
        return -1;
    }

    @Override
    public int getDefence() {
        return warrior.getDefence();
    }

    @Override
    public int getDamage() {
        return warrior.getDamage();
    }

    @Override
    public boolean isShoot() {
        return warrior.isShoot();
    }

    @Override
    public boolean isFly() {
        return warrior.isFly();
    }

    public boolean isFriendly() {
        return friendly;
    }

    @Override
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
        if (count < 1)
            destroy();
    }

    public int getCount() {
        return count;
    }

    public void resetStep() {
        if (isFly())
            this.step = 6;
        else
            this.step = warrior.getStep();
    }

    public boolean flyTo(MapPoint point) {
        int x = point.getX();
        int y = point.getY();
        for (int i = (x - 1 < 0) ? 0 : x - 1; i <= ((x + 1 < 6) ? x + 1 : 6); i++) {
            for (int j = (y - 1 < 0) ? 0 : y - 1; j <= ((y + 1 < 5) ? y + 1 : 5); j++) {
                if (mover.teleport(this, point.getGlade().getMapPoint(i, j))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void moveInDirection(MapPoint point) {
        mover.moveInDirection(this, point);
        this.reduceStep(1);
    }

}
