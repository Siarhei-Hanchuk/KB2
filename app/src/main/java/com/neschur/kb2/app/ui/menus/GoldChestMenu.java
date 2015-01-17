package com.neschur.kb2.app.ui.menus;

import android.app.Activity;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;

/**
 * Created by siarhei on 12.1.15.
 */
public class GoldChestMenu extends Menu {
    final int COUNT = 2;
    private GoldChest chest;

    public GoldChestMenu(Activity activity, Entity entity, GameController gameController) {
        super(activity, gameController);
        this.chest = (GoldChest) entity;
    }

    public String getItemDescription(int i) {
        switch (i) {
            case 0:
                return resources.getString(R.string.entity_menus_goldchest_item1);
            case 1:
                return resources.getString(R.string.entity_menus_goldchest_item2);
            default:
                return null;
        }
    }

    @Override
    public boolean select(int i) {
        switch (i) {
            case 0:
                player.changeMoney(+chest.getGold());
            case 1:
                player.changeAuthority(+chest.getAuthority());
        }
        chest.destroy();
        return true;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public boolean withMoney() {
        return false;
    }
}
