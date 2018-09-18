package by.siarhei.kb2.app.ui.menus;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.entities.GoldChest;
import by.siarhei.kb2.app.server.entities.Magician;
import by.siarhei.kb2.app.server.Game;

public class MenuFactory3 {
    private final Game game;
    private final I18n i18n;

    public MenuFactory3(Game game, I18n i18n) {
        this.game = game;
        this.i18n = i18n;
    }

    public Menu getMenu(Entity entity) {
        if (entity instanceof City) {
            return new CityMenu(entity, game, i18n);
        }
        if (entity instanceof Magician) {
            return new MagicianMenu(entity, game, i18n);
        }
        if (entity instanceof GoldChest && !((GoldChest) entity).isBonus()) {
            return new GoldChestMenu(entity, game, i18n);
        }
        return null;
    }

    public Menu getWorkersMenu() {
        return new WorkersMenu(game, i18n);
    }

    public Menu getCountryMenu() {
        return new CountryMenu(game, i18n);
    }

    public Menu getArmyFireMenu() {
        return new ArmyFireMenu(game, i18n);
    }
}
