package com.neschur.kb2.app.ui.menus;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.models.Game;
import com.neschur.kb2.app.models.Player;

public abstract class Menu {
    final Game game;
    final Player player;
    int menuMode = 0;

    Menu(Game game) {
        this.game = game;
        this.player = game.getPlayer();
    }

    public abstract int getCount();

    public abstract String getItemDescription(int i);

    public abstract boolean select(int i);

    public int getMenuMode() {
        return 0;
    }

    public void resetMenuMode() {
        menuMode = 0;
    }

    public boolean withExit() {
        return true;
    }

    public boolean withMoney() {
        return false;
    }

    String menuItem(String id, int price) {
        return I18n.translate(id) + "($" + price + ")";
    }

    String menuItem(String id) {
        return I18n.translate(id);
    }
}
