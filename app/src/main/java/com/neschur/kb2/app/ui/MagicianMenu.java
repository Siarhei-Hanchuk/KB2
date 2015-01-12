package com.neschur.kb2.app.ui;

import android.app.Activity;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

/**
 * Created by siarhei on 12.1.15.
 */
public class MagicianMenu extends Menu {
    final static int COUNT = 2;

    public MagicianMenu(Activity activity, Entity magician, GameController gameController){
        super(activity, gameController);
    }

    @Override
    public String getItemDescription(int i) {
        switch(i) {
            case 0:
                return resources.getString(R.string.entity_menus_magician_item1);
            case 1:
                return resources.getString(R.string.entity_menus_magician_item2);
            default:
                return null;
        }
    }

    @Override
    public boolean select(int i) {
        switch (i) {
            case 1:
                player.upMagicPower();
                return true;
        }
        return false;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
