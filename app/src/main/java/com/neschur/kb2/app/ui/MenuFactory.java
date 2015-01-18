package com.neschur.kb2.app.ui;

import android.app.Activity;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.Magician;
import com.neschur.kb2.app.ui.menus.CityMenu;
import com.neschur.kb2.app.ui.menus.GoldChestMenu;
import com.neschur.kb2.app.ui.menus.MagicianMenu;
import com.neschur.kb2.app.ui.menus.Menu;

/**
 * Created by siarhei on 14.1.15.
 */
public class MenuFactory {
    private static Activity activity;
    private static GameController gameController;

    public static void create(Activity activity, GameController gameController) {
        MenuFactory.activity = activity;
        MenuFactory.gameController = gameController;
    }

    public static Menu getMenu(Entity entity) {
        if (entity instanceof City) {
            return new CityMenu(activity, entity, gameController);
        }
        if (entity instanceof Magician) {
            return new MagicianMenu(activity, entity, gameController);
        }
        if (entity instanceof GoldChest && !((GoldChest) entity).isBonus()) {
            return new GoldChestMenu(activity, entity, gameController);
        }
        return null;
    }
}
