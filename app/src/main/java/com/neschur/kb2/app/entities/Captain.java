package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public class Captain extends Entity {

    public Captain(Country country, int x, int y) {
        super(country, x, y);
    }

    @Override
    public int getID() {
        return R.drawable.capitan;
    }
}
