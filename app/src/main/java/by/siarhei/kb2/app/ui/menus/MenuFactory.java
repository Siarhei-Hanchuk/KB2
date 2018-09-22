package by.siarhei.kb2.app.ui.menus;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.entities.GoldenChest;
import by.siarhei.kb2.app.server.entities.Magician;
import by.siarhei.kb2.app.server.Game;
import by.siarhei.kb2.app.server.models.MapPoint;

public class MenuFactory {
    private final Game game;
    private final I18n i18n;

    public MenuFactory(Game game, I18n i18n) {
        this.game = game;
        this.i18n = i18n;
    }

    public Menu getMenu(MapPoint mapPoint) {
        Entity entity = mapPoint.getEntity();
        if (entity instanceof City) {
            return new CityMenu(mapPoint, game, i18n);
        }
        if (entity instanceof Magician) {
            return new MagicianMenu(entity, game, i18n);
        }
        if (entity instanceof GoldenChest && !((GoldenChest) entity).isBonus()) {
            return new GoldChestMenu(mapPoint, game, i18n);
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
