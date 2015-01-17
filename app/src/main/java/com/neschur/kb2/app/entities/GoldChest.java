package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public class GoldChest extends Entity {
    private Integer gold = 0;

    public GoldChest(Country country, int x, int y, int min, int max) {
        super(country, x, y);
        gold = (int) ((max - min) * Math.random() + min);
    }

    @Override
    public int getID() {
        return R.drawable.goldchest;
    }

    public int getGold() {
        return gold;
    }

    public int getAuthority() {
        return gold / 50;
    }
}
