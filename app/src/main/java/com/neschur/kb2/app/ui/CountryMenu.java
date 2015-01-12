package com.neschur.kb2.app.ui;

import android.app.Activity;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;

/**
 * Created by siarhei on 12.1.15.
 */
public class CountryMenu extends Menu {
    public CountryMenu(Activity activity, GameController gameController) {
        super(activity, gameController);
    }

    @Override
    public String getItemDescription(int i) {
        switch (i) {
            case 0:
                return resources.getString(R.string.menus_country_item1);
            case 1:
                return resources.getString(R.string.menus_country_item2);
            case 2:
                return resources.getString(R.string.menus_country_item3);
            case 3:
                return resources.getString(R.string.menus_country_item4);
            case 4:
                return resources.getString(R.string.menus_country_item5);
            default:
                return null;
        }
    }

    @Override
    public boolean select(int i) {
        player.changeCountry(gameController.getWorld().getCountry(i));
        return true;
    }

    @Override
    public int getCount() {
        return player.getAvailableCountry();
    }
}
