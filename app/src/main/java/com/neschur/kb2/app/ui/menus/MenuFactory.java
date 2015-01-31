package com.neschur.kb2.app.ui.menus;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.controllers.MainController;
import com.neschur.kb2.app.entities.Captain;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.Magician;

public class MenuFactory {
    private static MainController mainController;

    public static void create(MainController mainController) {
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

    public static Menu getWorkersMenu() {
        return new WorkersMenu(getGameController());
    }

    public static Menu getCountryMenu() {
        return new CountryMenu(getGameController());
    }

    private static GameController getGameController() {
        return mainController.getGameController();
    }
}
