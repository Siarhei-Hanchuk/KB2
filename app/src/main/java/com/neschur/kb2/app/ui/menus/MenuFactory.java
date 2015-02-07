package com.neschur.kb2.app.ui.menus;

import com.neschur.kb2.app.controllers.GameOwner;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.Magician;

public class MenuFactory {
    private final GameOwner gameOwner;

    public MenuFactory(GameOwner gameOwner) {
        this.gameOwner = gameOwner;
    }

    public Menu getMenu(Entity entity) {
        if (entity instanceof City) {
            return new CityMenu(entity, gameOwner.getGame(), gameOwner.i18n());
        }
        if (entity instanceof Magician) {
            return new MagicianMenu(entity, gameOwner.getGame(), gameOwner.i18n());
        }
        if (entity instanceof GoldChest && !((GoldChest) entity).isBonus()) {
            return new GoldChestMenu(entity, gameOwner.getGame(), gameOwner.i18n());
        }
        return null;
    }

    public Menu getWorkersMenu() {
        return new WorkersMenu(gameOwner.getGame(), gameOwner.i18n());
    }

    public Menu getCountryMenu() {
        return new CountryMenu(gameOwner.getGame(), gameOwner.i18n());
    }
}
