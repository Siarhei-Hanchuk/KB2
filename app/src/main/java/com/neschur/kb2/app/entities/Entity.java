package com.neschur.kb2.app.entities;

/**
 * Created by siarhei on 2.6.14.
 */

import com.neschur.kb2.app.countries.Country;

public abstract class Entity {
    private Country country;
    protected int x;
    protected int y;

    public Entity(Country country, int x, int y) {
        country.getMapPoint(x, y).setEntity(this);
        this.country = country;
        this.x = x;
        this.y = y;
    }

    public abstract int getID();

    public void destroy() {
        country.getMapPoint(x, y).setEntity(null);
    }

    public void step(int x, int y) {
        move(this.x + (int) Math.signum(x), this.y + (int) Math.signum(y));
    }

    public void move(int x, int y) {
        destroy();
        this.x = x;
        this.y = y;
        country.getMapPoint(x, y).setEntity(this);
    }
}

