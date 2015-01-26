package com.neschur.kb2.app.ui.menus;

import android.app.Activity;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;

public class CityMenu extends Menu {
    private final int COUNT = 7;
    private final int WORKERS_COUNT = 4;
    private final int[] PRICE_WORKERS = {500, 500, 800, 800};
    private final int PRICE_NAVE = 500;
    private final int PRICE_WALLKICK = 3000;
    private City city;

    public CityMenu(Activity activity, Entity city, GameController gameController) {
        super(activity, gameController);
        this.city = (City) city;
    }

    @Override
    public String getItemDescription(int i) {
        switch (menuMode) {
            case 0:
                switch (i) {
                    case 0:
                        return resources.getString(R.string.entity_menus_city_item1);
                    case 1:
                        if (gameController.getNave())
                            return resources.getString(R.string.entity_menus_city_item2a);
                        return menuItem("entity_menus_city_item2", PRICE_NAVE);
                    case 2:
                        return resources.getString(R.string.entity_menus_city_item3);
                    case 3:
                        return resources.getString(R.string.entity_menus_city_item4);
                    case 4:
                        if (player.isWallkick())
                            return "-";
                        return menuItem("entity_menus_city_item5", PRICE_WALLKICK);
                    case 5:
                        return resources.getString(R.string.entity_menus_city_item6);
                    case 6:
                        return resources.getString(R.string.entity_menus_city_item7);
                }
            case 1:
                return menuItem("entity_menus_city_workers_item" + (i + 1), PRICE_WORKERS[i]) + ": " + city.getWorkers(i);
        }
        return null;
    }

    @Override
    public boolean select(int i) {
        switch (menuMode) {
            case 0:
                switch (i) {
                    case 1:
                        if (gameController.getNave()) {
                            gameController.destroyNave();
                        } else {
                            buyNave();
                        }
                        return false;
                    case 4:
                        if (!player.isWallkick()) {
                            player.changeMoney(-PRICE_WALLKICK);
                            player.setWallkick();
                        }
                        return false;
                    case 5:
                        menuMode = 1;
                        return false;

                }
                return false;
            case 1:
                if (city.getWorkers(i) > 0) {
                    if (player.changeMoney(-PRICE_WORKERS[i])) {
                        player.changeWorker(i, 1);
                        city.changeWorkers(i, -1);
                    }
                }
                return false;
            default:
                return false;
        }
    }

    private void buyNave() {
        player.changeMoney(-PRICE_NAVE);
        int x;
        int y;
        for (x = city.getX() - 1; x <= city.getX() + 1; x++) {
            for (y = city.getY() - 1; y <= city.getY() + 1; y++) {
                if (city.getCountry().getMapPoint(x, y).getLand() == R.drawable.water) {
                    gameController.createNave(x, y);
                    return;
                }
            }
        }

    }

    @Override
    public int getCount() {
        switch (menuMode) {
            case 1:
                return WORKERS_COUNT;
            default:
                return COUNT;
        }
    }

    @Override
    public int getMenuMode() {
        return menuMode;
    }

    public boolean withMoney() {
        return true;
    }
}
