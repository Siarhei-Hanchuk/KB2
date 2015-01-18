package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public class Nave extends Entity {
    public Nave(Country country, int x, int y) {
        super(country, x, y);
        this.country = country;
    }

    @Override
    public int getID() {
        return R.drawable.nave;
    }

    public void move(int x, int y) {
        move(x, y, null);
    }

    public void move(int x, int y, Country country) {
        this.country.getMapPoint(this.x, this.y).setEntity(null);
        if (country != null)
            this.country = country;
        this.country.getMapPoint(x, y).setEntity(this);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
