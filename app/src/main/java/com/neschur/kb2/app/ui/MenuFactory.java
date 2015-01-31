package com.neschur.kb2.app.ui;

import android.app.Activity;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.controllers.MainController;
import com.neschur.kb2.app.entities.Captain;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.Magician;
import com.neschur.kb2.app.ui.menus.CaptainMenu;
import com.neschur.kb2.app.ui.menus.CityMenu;
import com.neschur.kb2.app.ui.menus.GoldChestMenu;
import com.neschur.kb2.app.ui.menus.MagicianMenu;
import com.neschur.kb2.app.ui.menus.Menu;

public class MenuFactory {
    private static Activity activity;
    private static MainController mainController;

    public static void create(Activity activity, MainController mainController) {
        MenuFactory.activity = activity;
        MenuFactory.mainController = mainController;
    }

    public static Menu getMenu(Entity entity) {
        if (entity instanceof City) {
            return new CityMenu(entity, getGameController());
        }
        if (entity instanceof Magician) {
            return new MagicianMenu(entity, getGameController());
        }
        if (entity instanceof GoldChest && !((GoldChest) entity).isBonus()) {
            return new GoldChestMenu(entity, getGameController());
        }
        if (entity instanceof Captain) {
            return new CaptainMenu(entity, getGameController());
        }
        return null;
    }

    private static GameController getGameController() {
        return mainController.getGameController();
    }
}
