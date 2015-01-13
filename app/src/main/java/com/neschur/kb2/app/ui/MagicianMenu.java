package com.neschur.kb2.app.ui;

import android.app.Activity;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

/**
 * Created by siarhei on 12.1.15.
 */
public class MagicianMenu extends Menu {
    final static int COUNT = 6;

    public MagicianMenu(Activity activity, Entity magician, GameController gameController) {
        super(activity, gameController);
    }

    @Override
    public String getItemDescription(int i) {
        switch (i) {
            case 0:
                return resources.getString(R.string.entity_menus_magician_item1);
            case 1:
                return resources.getString(R.string.entity_menus_magician_item2);
            case 2:
                return "-";
            case 3:
                return "-";
            case 4:
                return "-";
            case 5:
                return "-";
            default:
                return null;
        }
    }

    @Override
    public boolean select(int i) {
        switch (i) {
            case 0:
                player.upUsedMagicianCount();
                return true;
            case 1:
                player.upMagicPower();
                player.upUsedMagicianCount();
                return true;
        }
        return false;
    }

    @Override
    public int getCount() {
        return Math.min(player.getUsedMagicianCount() + 1, COUNT);
    }

    @Override
    public boolean withExit() {
        return false;
    }
}
