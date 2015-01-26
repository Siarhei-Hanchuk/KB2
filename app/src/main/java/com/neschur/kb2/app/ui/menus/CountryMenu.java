package com.neschur.kb2.app.ui.menus;

import android.app.Activity;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.controllers.GameController;

public class CountryMenu extends Menu {
    public CountryMenu(Activity activity, GameController gameController) {
        super(activity, gameController);
    }

    @Override
    public String getItemDescription(int i) {
        return I18n.translate("menus_country_item" + (i + 1));
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

    @Override
    public boolean withMoney() {
        return false;
    }
}
