package com.neschur.kb2.app.ui.menus;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.models.Game;

public class CountryMenu extends Menu {
    CountryMenu(Game game, I18n i18n) {
        super(game, i18n);
    }

    @Override
    public String getItemDescription(int i) {
        return i18n.translate("countries_country" + (i + 1));
    }

    @Override
    public boolean select(int i) {
        game.changeCountry(i);
        return true;
    }

    @Override
    public int getCount() {
        return player.getAvailableCountry();
    }
}
