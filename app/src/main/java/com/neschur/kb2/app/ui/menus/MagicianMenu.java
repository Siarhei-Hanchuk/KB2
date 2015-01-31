package com.neschur.kb2.app.ui.menus;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

public class MagicianMenu extends Menu {
    private final int PRICE_MAGIC_POWER = 5000;
    private final int PRICE_WORKERS = 1000;
    private final int PRICE_MOVE_TO_COUNTRY = 5000;
    private final int PRICE_TORNADO = 1000;

    MagicianMenu(Entity magician, GameController gameController) {
        super(gameController);
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
        }
        return null;
    }

    @Override
    public boolean select(int i) {
        switch (menuMode) {
            case 0:
                switch (i) {
                    case 0:
                        menuMode = 1;
                        return false;
                    case 1:
                        if (player.changeMoney(-PRICE_MAGIC_POWER)) {
                            player.upMagicPower();
                            player.upUsedMagicianCount();
                        }
                        return true;
                }
            case 1:
                player.changeMoney(-PRICE_WORKERS);
                player.changeWorker(i, +player.getMagicPower());
                player.upUsedMagicianCount();
                return true;
        }
        return false;
    }

    @Override
    public int getCount() {
        return Math.min(player.getUsedMagicianCount() + 1, 6);
    }

    @Override
    public boolean withExit() {
        return false;
    }

    public boolean withMoney() {
        return true;
    }
}
