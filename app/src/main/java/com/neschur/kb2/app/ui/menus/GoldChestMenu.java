package com.neschur.kb2.app.ui.menus;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.models.Game;

public class GoldChestMenu extends Menu {
    private final GoldChest chest;

    GoldChestMenu(Entity entity, Game game, I18n i18n) {
        super(game, i18n);
        this.chest = (GoldChest) entity;
    }

    @Override
    public String getItemDescription(int i) {
        if (i == 0)
            return i18n.translate("entity_goldchest_menu_item1", chest.getGold());
        else
            return i18n.translate("entity_goldchest_menu_item2", chest.getAuthority());
    }

    @Override
    public boolean select(int i) {
        switch (i) {
            case 0:
                player.changeMoney(+chest.getGold());
            case 1:
                player.changeAuthority(+chest.getAuthority());
        }
        chest.destroy();
        return true;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
