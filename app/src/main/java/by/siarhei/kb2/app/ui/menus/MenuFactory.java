package by.siarhei.kb2.app.ui.menus;

import by.siarhei.kb2.app.controllers.GameOwner;
import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.entities.GoldenChest;
import by.siarhei.kb2.app.server.entities.Magician;

public class MenuFactory {
    private final GameOwner gameOwner;

    public MenuFactory(GameOwner gameOwner) {
        this.gameOwner = gameOwner;
    }

    public Menu getMenu(Entity entity) {
//        if (entity instanceof City) {
//            return new CityMenu(entity, gameOwner.getGame(), gameOwner.i18n());
//        }
        if (entity instanceof Magician) {
            return new MagicianMenu(entity, gameOwner.getGame(), gameOwner.i18n());
        }
        if (entity instanceof GoldenChest && !((GoldenChest) entity).isBonus()) {
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

    public Menu getArmyFireMenu() {
        return new ArmyFireMenu(gameOwner.getGame(), gameOwner.i18n());
    }
}
