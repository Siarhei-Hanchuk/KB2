package com.neschur.kb2.app.entities;

/**
 * Created by siarhei on 2.6.14.
 */

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Country country;

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

    public void moveTo(int x, int y) {
        int directionX = (int) Math.signum(x - this.x);
        int directionY = (int) Math.signum(y - this.y);
        move(this.x + directionX, this.y + directionY);
    }

    public void move(int x, int y) {
        if (getCountry().getMapPoint(x, y).getEntity() == null &&
                getCountry().getMapPoint(x, y).getLand() == R.drawable.land) {
            destroy();
            this.x = x;
            this.y = y;
            getCountry().getMapPoint(x, y).setEntity(this);
        }
    }

    public Country getCountry() {
        return country;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

