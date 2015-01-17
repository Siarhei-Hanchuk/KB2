package com.neschur.kb2.app.entities;

import android.app.Activity;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.Player;

public class MapNext extends Entity {

    public MapNext(Country country, int x, int y) {
        super(country, x, y);
    }

    @Override
    public int getID() {
        return R.drawable.map;
    }
}
