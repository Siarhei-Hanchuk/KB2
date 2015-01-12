package com.neschur.kb2.app.ui;

import android.app.Activity;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;

public class CityMenu extends Menu {
    final int COUNT = 7;
    final int WORKERS_COUNT = 4;
    final int[] PRICE_WORKERS = {500, 500, 800, 800};
    private City city;
    private int menuMode = 0;

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
                            return resources.getString(R.string.entity_menus_city_item2a) +
                                    "($" + 500 + ")";
                        return resources.getString(R.string.entity_menus_city_item2);
                    case 2:
                        return resources.getString(R.string.entity_menus_city_item3);
                    case 3:
                        return resources.getString(R.string.entity_menus_city_item4);
                    case 4:
                        if (player.isWallkick())
                            return "-";
                        return resources.getString(R.string.entity_menus_city_item5);
                    case 5:
                        return resources.getString(R.string.entity_menus_city_item6);
                    case 6:
                        return resources.getString(R.string.entity_menus_city_item7);
                }
            case 1:
                switch (i) {
                    case 0:
                        return resources.getString(R.string.entity_menus_city_workers_item1) + "($" + 500 + ")";
                    case 1:
                        return resources.getString(R.string.entity_menus_city_workers_item2) + "($" + 500 + ")";
                    case 2:
                        return resources.getString(R.string.entity_menus_city_workers_item3) + "($" + 800 + ")";
                    case 3:
                        return resources.getString(R.string.entity_menus_city_workers_item4) + "($" + 800 + ")";
                }
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
                            player.changeMoney(-500);
                            gameController.createNave(4, 5);
                        }
                        return false;
                    case 4:
                        if (!player.isWallkick()) {
                            player.changeMoney(-3000);
                            player.setWallkick();
                        }
                        return false;
                    case 5:
                        menuMode = 1;
                        return false;

                }
            case 1:
                  if(city.getWorkers(i) > 0 ){
                      if(player.changeMoney(-PRICE_WORKERS[i])){
                          player.changeWorker(i, 1);
                          city.changeWorkers(i, -1);
                      }
                  }
        }
        return false;
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
}
