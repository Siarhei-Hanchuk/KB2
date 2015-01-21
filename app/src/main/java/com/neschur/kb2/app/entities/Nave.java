package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public class Nave extends Entity {
    public Nave(Country country, int x, int y) {
        super(country, x, y);
    }

    @Override
    public int getID() {
        return R.drawable.nave;
    }

    public void move(int x, int y) {
        move(x, y, null);
    }

    public void move(int x, int y, Country country) {
        this.glade.getMapPoint(this.x, this.y).setEntity(null);
        if (country != null)
            this.glade = country;
        this.glade.getMapPoint(x, y).setEntity(this);
        this.x = x;
        this.y = y;
    }
}
