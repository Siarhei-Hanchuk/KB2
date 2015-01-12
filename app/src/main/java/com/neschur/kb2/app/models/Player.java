package com.neschur.kb2.app.models;

import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.entities.Nave;

/**
 * Created by siarhei on 6.6.14.
 */

public class Player {
    private boolean wallkick;
    private int workers[] = new int[4];
    private int money;
    private int authority;
    private Country country;
    private int usedMagicianCount = 0;
    private int magicPower;
    private int availableCountry = 1;
    private Nave nave;

    private int X;
    private int Y;

    private void easy() {
        X = 5;
        Y = 5;
        wallkick = false;
        money = 20000;
        authority = 50;
        magicPower = 2;
        for (int i = 0; i < 10; i++) {
            workers[0] = 4;
            workers[1] = 6;
            workers[2] = 0;
            workers[3] = 3;
        }
    }

    private void debug() {
        availableCountry = 5;
    }

    public Player() {
        easy();
        debug();
    }

    public void move(int x, int y) {
        this.X = x;
        this.Y = y;
        if (getNave() != null) {
            getNave().move(x, y);
        }
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void changeCountry(Country country) {
        this.country = country;
        X = 2;
        Y = 2;
        if (inNave()) {
            nave.move(2, 2, country);
        }
    }

    public Country getCountry() {
        return country;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void changeMoney(int d) {
        money += d;
    }

    public Integer getMoney() {
        return money;
    }

    public boolean isWallkick() {
        return wallkick;
    }

    public void setWallkick() {
        this.wallkick = true;
    }

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public boolean inNave() {
        return nave != null;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void upMagicPower() {
        this.magicPower++;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void changeAuthority(int d) {
        authority += d;
    }

    public void upAvailableCountry() {
        this.availableCountry++;
    }

    public int getAvailableCountry() {
        return availableCountry;
    }

    public int getUsedMagicianCount() {
        return usedMagicianCount;
    }

    public void upUsedMagicianCount() {
        this.usedMagicianCount++;
    }
//
//    public void upWorker(int id, int count){
//        workers[id]+=count;
//    }
//
//    public int getWorker(int id){
//        return workers[id];
//    }
}

