package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

/**
 * Created by siarhei on 18.1.15.
 */
public class Sorcerer extends Entity {

    public Sorcerer(Country country, int x, int y) {
        super(country, x, y);
    }

    @Override
    public int getID() {
        return R.drawable.sorcerer;
    }
}
