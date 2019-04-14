package by.siarhei.kb2.app.ui.menus;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.countries.Country;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.entities.Magician;
import by.siarhei.kb2.app.server.models.Game;

public class MagicianMenu extends Menu {
    private final int PRICE_MAGIC_POWER = 5000;
    private final int PRICE_WORKERS = 1000;
    private final int PRICE_MOVE_TO_COUNTRY = 5000;
    private final int PRICE_TORNADO = 10000;
    private final Magician magician;

    MagicianMenu(Entity magician, Game game, I18n i18n) {
        super(game, i18n);
        this.magician = (Magician) magician;
    }

    @Override
    public String getItemDescription(int i) {
        switch (menuMode) {
            case 0:
                switch (i) {
                    case 0:
                        return menuItem("entity_magician_menu_item1", PRICE_WORKERS);
                    case 1:
                        return menuItem("entity_magician_menu_item2", PRICE_MAGIC_POWER);
                    case 2:
                        return menuItem("entity_magician_menu_item3", PRICE_MOVE_TO_COUNTRY);
                    case 3:
                        return menuItem("entity_magician_menu_item4");
                    case 4:
                        return menuItem("entity_magician_menu_item5", PRICE_TORNADO);
                    case 5:
                        return "-";
                    default:
                        return null;
                }
            case 1:
                return menuItem("entity_menus_city_workers_item" + (i + 1));
            case 2:
                return i18n.translate("countries_country" + (i - 1));
        }
        return null;
    }

    @Override
    public int getItemImageId(int i) {
        return 0;
    }

    @Override
    public boolean select(int i) {
        int countryId = player.getCountry().getId();
        switch (menuMode) {
            case 0:
                switch (i) {
                    case 0:
                        menuMode = 1;
                        return false;
                    case 1:
                        if (player.changeMoney(-PRICE_MAGIC_POWER)) {
                            player.getMagic().upMagicPower();
                            magician.upUsedMagicianCount(countryId);
                        }
//                        TODO:
//                        magician.destroy();
                        return true;
                    case 2:
                        if (player.changeMoney(-PRICE_MOVE_TO_COUNTRY)) {
                            menuMode = 2;
                            magician.upUsedMagicianCount(countryId);
                        }
                        // TODO:
//                        magician.destroy();
                        return true;
                    case 4:
                        if (player.changeMoney(-PRICE_TORNADO)) {
                            player.getMagic().changeTornado(+1);
                            magician.upUsedMagicianCount(countryId);
                        }
                        // TODO:
//                        magician.destroy();
                        return true;
                }
            case 1:
                player.changeMoney(-PRICE_WORKERS);
                player.changeWorker(i, +player.getMagic().getMagicPower());
                magician.upUsedMagicianCount(countryId);
                // TODO:
//                magician.destroy();
                return true;
        }
        return false;
    }

    @Override
    public int getCount() {
        switch (menuMode) {
            case 0:
                Country country = game.getPlayer().getCountry();
                return Math.min(magician.getUsedMagicianCount(country.getId()) + 1, 6);
            case 1:
                return 4;
            case 2:
                return 5;
        }
        return 0;
    }

    @Override
    public boolean withExit() {
        return false;
    }

    @Override
    public boolean withMoney() {
        return true;
    }
}
