package by.siarhei.kb2.app.ui.menus;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.warriors.WarriorSquad;

public class CityMenu extends Menu {
    private final int[] PRICE_WORKERS = {500, 500, 800, 800};
    private final int[] PRICE_MAGIC = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
    private final int PRICE_NAVE = 500;
    private final int PRICE_WALLKICK = 3000;
    private final City city;
    private final MapPoint mapPoint;

    CityMenu(MapPoint mapPoint, Game game, I18n i18n) {
        super(game, i18n);
        this.city = (City) mapPoint.getEntity();
        this.mapPoint = mapPoint;
    }

    public String getCityName() {
        return i18n.translate("entity_city_names_name" + city.getNameId());
    }

    @Override
    public String getItemDescription(int i) {
        switch (menuMode) {
            case 0:
                switch (i) {
                    case 0:
                        return i18n.translate(R.string.entity_city_menu_item1);
                    case 1:
                        if (game.naveExists())
                            return i18n.translate(R.string.entity_city_menu_item2a);
                        return menuItem("entity_city_menu_item2", PRICE_NAVE);
                    case 2:
                        return i18n.translate(R.string.entity_city_menu_item3);
                    case 3:
                        if (city.getMagic() < 7)
                            return menuItem("magic_battle_magic" + (city.getMagic() + 1),
                                    PRICE_MAGIC[city.getMagic()]);
                        else
                            return menuItem("magic_hiking_magic" + (city.getMagic() - 7 + 1),
                                    PRICE_MAGIC[city.getMagic()]);
                    case 4:
                        if (player.hasWallDestroyer())
                            return "-";
                        return menuItem("entity_city_menu_item5", PRICE_WALLKICK);
                    case 5:
                        return i18n.translate(R.string.entity_city_menu_item6);
                    case 6:
                        return i18n.translate(R.string.entity_city_menu_item7);
                }
            case 1:
                return menuItem("entity_city_menu_workers_item" + (i + 1), PRICE_WORKERS[i])
                        + ": " + city.getWorkers(i);
            case 2:
                if (i == 0) {
                    return i18n.translate(R.string.battle_begin_castle) + " " +
                            i18n.translate("entity_castle_names_name" + city.getCastle().getNameId());

                } else {
                    WarriorSquad squad = city.getCastle().getWarriorSquad(i - 1);
                    if (squad != null) {
                        return i18n.translate("army_names_" + squad.getWarrior().getTextId());
                    }
                }
        }
        return null;
    }

    @Override
    public int getItemImageId(int i) {
        return 0;
    }

    @Override
    public boolean select(int i) {
        switch (menuMode) {
            case 0:
                switch (i) {
                    case 1:
                        if (game.naveExists()) {
                            game.destroyNave();
                        } else {
                            buyNave();
                        }
                        return false;
                    case 2:
                        menuMode = 2;
                        return false;
                    case 3:
                        if (player.changeMoney(-PRICE_MAGIC[city.getMagic()]))
                            player.getMagic().upMagic(city.getMagic());
                    case 4:
                        if (!player.hasWallDestroyer()) {
                            player.changeMoney(-PRICE_WALLKICK);
                            player.setWallDestroyer();
                        }
                        return false;
                    case 5:
                        menuMode = 1;
                        return false;
                    case 7:
                        return true;

                }
                return false;
            case 1:
                if(i < 4) {
                    if (city.getWorkers(i) > 0) {
                        if (player.changeMoney(-PRICE_WORKERS[i])) {
                            player.changeWorker(i, 1);
                            city.changeWorkers(i, -1);
                        }
                    }
                } else {
                    menuMode = 0;
                }
                return false;
            case 2:
                menuMode = 0;
            default:
                return false;
        }
    }

    private void buyNave() {
        player.changeMoney(-PRICE_NAVE);
        for (int x = mapPoint.getX() - 1; x <= mapPoint.getX() + 1; x++) {
            for (int y = mapPoint.getY() - 1; y <= mapPoint.getY() + 1; y++) {
                if (player.getCountry().getMapPoint(x, y).getLand() == R.drawable.water) {
                    game.createNave(player.getCountry(), x, y);
                    return;
                }
            }
        }
        return;
    }

    @Override
    public int getCount() {
        switch (menuMode) {
            case 1:
                return 4;
            case 2:
                return 6;
            default:
                return 7;
        }
    }

    @Override
    public int getMenuMode() {
        return menuMode;
    }

    @Override
    public boolean withMoney() {
        return true;
    }
}
