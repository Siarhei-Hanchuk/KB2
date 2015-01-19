package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.countries.Country;

public class Castle extends Entity {
    private int id;

    public Castle(Country country, int x, int y, int id) {
        super(country, x, y);
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }
}
