package com.neschur.kb2.app.ui.menus;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.entities.Captain;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.Magician;

public class MenuFactory {
    private final ViewController viewController;

    public MenuFactory(ViewController viewController) {
        this.viewController = viewController;
    }

    public Menu getMenu(Entity entity) {
        if (entity instanceof City) {
            return new CityMenu(entity, viewController.getGameController());
        }
        if (entity instanceof Magician) {
            return new MagicianMenu(entity, viewController.getGameController());
        }
        if (entity instanceof GoldChest && !((GoldChest) entity).isBonus()) {
            return new GoldChestMenu(entity, viewController.getGameController());
        }
        if (entity instanceof Captain) {
            return new CaptainMenu(entity, viewController.getGameController());
        }
        return null;
    }

    public Menu getWorkersMenu() {
        return new WorkersMenu(viewController.getGameController());
    }

    public Menu getCountryMenu() {
        return new CountryMenu(viewController.getGameController());
    }
}
