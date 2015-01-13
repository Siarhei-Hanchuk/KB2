package com.neschur.kb2.app.entities;

import android.app.Activity;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.ui.GoldChestMenu;
import com.neschur.kb2.app.ui.Menu;

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

    @Override
    public Menu getMenu(Activity activity, GameController gameController) {
        return new GoldChestMenu(activity, this, gameController);
    }

    public int getGold() {
        return gold;
    }

    public int getAuthority() {
        return (int) gold / 50;
    }
}
