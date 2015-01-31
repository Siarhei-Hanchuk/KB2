package com.neschur.kb2.app.ui.menus;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.controllers.MainController;
import com.neschur.kb2.app.entities.Captain;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.Magician;

public class MenuFactory {
    private final MainController mainController;

    public MenuFactory(MainController mainController) {
        this.mainController = mainController;
    }

    public Menu getMenu(Entity entity) {
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

    public Menu getWorkersMenu() {
        return new WorkersMenu(getGameController());
    }

    public Menu getCountryMenu() {
        return new CountryMenu(getGameController());
    }

    private GameController getGameController() {
        return mainController.getGameController();
    }
}
