package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

import java.util.Random;

public class GoldChest extends Entity {
    private int gold = 0;
    private boolean bonus = false;

    public GoldChest(Country country, int x, int y, int min, int max) {
        super(country, x, y);
        gold = (int) ((max - min) * Math.random() + min);
        int rand = (new Random()).nextInt(10);
        if ( rand == 0 )
            bonus = true;
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

    public boolean isBonus() {
        return bonus;
    }
}
