package by.siarhei.kb2.app.server.models.battle;

import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.warriors.Warrior;

public class WarriorEntity implements Entity, Warrior {
    private final Warrior warrior;
    private final boolean friendly;
    private int step;
    private int count;
    private int defence;

    public WarriorEntity(Warrior warrior, int count, boolean friendly) {
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

    public boolean isPlayerEntity() {
        return friendly;
    }

    public boolean isOwn(WarriorEntity entity) {
        return friendly == entity.friendly;
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

    private void takeAttack(int damage) {
        defence -= damage;
        count = defence / warrior.getDefence();
    }

    public int getCount() {
        return count;
    }

    void resetStep() {
        if (isFly())
            this.step = 6;
        else
            this.step = warrior.getStep();
    }
}
