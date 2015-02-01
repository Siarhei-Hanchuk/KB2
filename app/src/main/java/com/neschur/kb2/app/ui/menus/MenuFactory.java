package com.neschur.kb2.app.ui.menus;

import com.neschur.kb2.app.controllers.GameOwner;
import com.neschur.kb2.app.entities.Captain;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.Magician;

public class MenuFactory {
    private final GameOwner controller;

    public MenuFactory(GameOwner controller) {
        this.controller = controller;
    }

    public Menu getMenu(Entity entity) {
        if (entity instanceof City) {
            return new CityMenu(entity, controller.getGameController());
        }
        if (entity instanceof Magician) {
            return new MagicianMenu(entity, controller.getGameController());
        }
        if (entity instanceof GoldChest && !((GoldChest) entity).isBonus()) {
            return new GoldChestMenu(entity, controller.getGameController());
        }
        if (entity instanceof Captain) {
            return new CaptainMenu(entity, controller.getGameController());
        }
        return null;
    }

    public Menu getWorkersMenu() {
        return new WorkersMenu(controller.getGameController());
    }

    public Menu getCountryMenu() {
        return new CountryMenu(controller.getGameController());
    }
}
