package by.siarhei.kb2.app.ui.menus;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.countries.Country;
import by.siarhei.kb2.app.server.entities.Magician;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.models.MapPoint;

public class MagicianMenu extends Menu {
    private final int PRICE_MAGIC_POWER = 5000;
    private final int PRICE_WORKERS = 1000;
    private final int PRICE_MOVE_TO_COUNTRY = 5000;
    private final int PRICE_TORNADO = 10000;
    private final int ITEM_WORKERS = 0;
    private final int ITEM_MAGIC_POWER = 1;
    private final int ITEM_MOVE_TO_COUNTRY = 2;
    private final int ITEM_ASK_FOR_SPELL = 3;
    private final int ITEM_TORNADO = 4;
    private final int MODE_MAIN = 0;
    private final int MODE_WORKERS = 1;
    private final int MODE_COUNTRIES = 2;
    private final MapPoint mapPoint;

    MagicianMenu(MapPoint mapPoint, Game game, I18n i18n) {
        super(game, i18n);
        this.mapPoint = mapPoint;
    }

    @Override
    public String getItemDescription(int i) {
        mapPoint.setEntity(null);
        switch (menuMode) {
            case MODE_MAIN:
                switch (i) {
                    case ITEM_WORKERS:
                        return menuItem("entity_magician_menu_item1", PRICE_WORKERS);
                    case ITEM_MAGIC_POWER:
                        return menuItem("entity_magician_menu_item2", PRICE_MAGIC_POWER);
                    case ITEM_MOVE_TO_COUNTRY:
                        return menuItem("entity_magician_menu_item3", PRICE_MOVE_TO_COUNTRY);
                    case ITEM_ASK_FOR_SPELL:
                        return menuItem("entity_magician_menu_item4");
                    case ITEM_TORNADO:
                        return menuItem("entity_magician_menu_item5", PRICE_TORNADO);
                    case 5:
                        return "-";
                    default:
                        return null;
                }
            case MODE_WORKERS:
                return menuItem("workers_item" + (i + 1));
            case MODE_COUNTRIES:
                return i18n.translate("countries_country" + (i + 1));
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
            case MODE_MAIN:
                switch (i) {
                    case ITEM_WORKERS:
                        if (player.changeMoney(-PRICE_WORKERS)) {
                            player.getMagicianStatus().upUsedMagicianCount(countryId);
                            menuMode = MODE_WORKERS;
                            return false;
                        }
                        return true;
                    case ITEM_MAGIC_POWER:
                        if (player.changeMoney(-PRICE_MAGIC_POWER)) {
                            player.getMagic().upMagicPower();
                            player.getMagicianStatus().upUsedMagicianCount(countryId);
                        }
                        return true;
                    case ITEM_MOVE_TO_COUNTRY:
                        if (player.changeMoney(-PRICE_MOVE_TO_COUNTRY)) {
                            menuMode = MODE_COUNTRIES;
                            player.getMagicianStatus().upUsedMagicianCount(countryId);
                            return false;
                        }
                        return true;
                    case ITEM_TORNADO:
                        if (player.changeMoney(-PRICE_TORNADO)) {
                            player.getMagic().changeTornado(+1);
                            player.getMagicianStatus().upUsedMagicianCount(countryId);
                        }
                        return true;
                }
            case MODE_WORKERS:
                int number = Math.min(player.getMagic().getMagicPower(), 1);
                player.changeWorker(i, +number);

                return true;
            case MODE_COUNTRIES:
                game.changeCountry(i);
                return true;
        }
        return false;
    }

    @Override
    public int getCount() {
        switch (menuMode) {
            case MODE_MAIN:
                Country country = game.getPlayer().getCountry();
                return Math.min(player.getMagicianStatus().getUsedMagicianCount(country.getId()) + 1, 6);
            case MODE_WORKERS:
                return 4;
            case MODE_COUNTRIES:
                return Math.min(maxAvailableCountry(), 5);
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

    public int maxAvailableCountry() {
        return player.getAvailableCountry() + 1;
    }
}
