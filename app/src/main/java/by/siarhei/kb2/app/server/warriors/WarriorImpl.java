package by.siarhei.kb2.app.server.warriors;

import java.io.Serializable;

class WarriorImpl implements Warrior, Serializable {
    private final String textId;
    private final int id;
    private final int defence;
    private final int damage;
    private final int step;
    private final boolean fly;
    private final boolean shoot;
    private final int countInShop;
    private final int priceInShop;

    public WarriorImpl(String textId, int id, int defence, int damage, int step, boolean fly,
                       boolean shoot, int count, int cost) {
        super();
        this.textId = textId;
        this.id = id;
        this.defence = defence;
        this.damage = damage;
        this.step = step;
        this.fly = fly;
        this.shoot = shoot;
        this.countInShop = count;
        this.priceInShop = cost;
    }

    @Override
    public String getTextId() {
        return textId;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getPriceInShop() {
        return priceInShop;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getStep() {
        return step;
    }

    @Override
    public boolean isFly() {
        return fly;
    }

    @Override
    public boolean isShoot() {
        return shoot;
    }

    @Override
    public int getCountInShop() {
        return countInShop;
    }
}
