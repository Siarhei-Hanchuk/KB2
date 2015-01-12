package com.neschur.kb2.app.entities;

import android.app.Activity;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.ui.MagicianMenu;
import com.neschur.kb2.app.ui.Menu;

public class Magician extends Entity {

    public Magician(Country country, int x, int y) {
        super(country, x, y);
    }

    @Override
    public int getID() {
        return R.drawable.magican;
    }

    public Menu getMenu(Activity activity, GameController gameController) {
        return new MagicianMenu(activity, this, gameController);
    }
}
