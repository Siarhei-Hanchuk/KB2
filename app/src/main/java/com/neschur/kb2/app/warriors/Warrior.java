package com.neschur.kb2.app.warriors;

import java.io.Serializable;

public class Warrior implements Serializable {
    private final String textId;
    private final int id;
    private final int defence;
    private final int damage;
    private final int step;
    private final boolean fly;
    private final boolean shoot;
    private final int countInShop;
    private final int priceInShop;

    public Warrior(String textId, int id, int defence, int damage, int step, boolean fly,
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

    public String getTextId() {
        return textId;
    }

    public int getId() {
        return id;
    }

    public int getPriceInShop() {
        return priceInShop;
    }

    public int getDefence() {
        return defence;
    }

    public int getDamage() {
        return damage;
    }

    public int getStep() {
        return step;
    }

    public boolean isFly() {
        return fly;
    }

    public boolean isShoot() {
        return shoot;
    }

    public int getCountInShop() {
        return countInShop;
    }
}
