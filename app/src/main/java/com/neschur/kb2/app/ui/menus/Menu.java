package com.neschur.kb2.app.ui.menus;

import android.app.Activity;
import android.content.res.Resources;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.models.Player;

public abstract class Menu {
    protected Activity activity;
    protected GameController gameController;
    protected Resources resources;
    protected Player player;
    protected int menuMode = 0;

    public Menu(Activity activity, GameController gameController) {
        this.activity = activity;
        this.gameController = gameController;
        this.resources = activity.getResources();
        this.player = gameController.getPlayer();
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
        return true;
    }

    protected String menuItem(String id, int price) {
        return I18n.translate(id) + "($" + price + ")";
    }

    protected String menuItem(String id) {
        return I18n.translate(id);
    }
}
