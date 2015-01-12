package com.neschur.kb2.app.warriors;

public class Warrior {
    private String name;
    private int defence;
    private int damage;
    private int step;
    private boolean fly;
    private boolean shoot;
    private int count;
    private int cost;

    public Warrior(String name, int defence, int damage, int step, boolean fly, boolean shoot, int count, int cost) {
        super();
        this.name = name;
        this.defence = defence;
        this.damage = damage;
        this.step = step;
        this.fly = fly;
        this.shoot = shoot;
        this.count = count;
        this.cost = cost;
    }
}
