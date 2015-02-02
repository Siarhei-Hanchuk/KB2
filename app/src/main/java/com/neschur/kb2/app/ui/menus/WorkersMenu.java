package com.neschur.kb2.app.ui.menus;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.models.Game;

public class WorkersMenu extends Menu {
    WorkersMenu(Game game, I18n i18n) {
        super(game, i18n);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public String getItemDescription(int i) {
        return i18n.translate("workers_item" + (i + 1)) + ": " + player.getWorker(i);
    }

    @Override
    public boolean select(int i) {
        game.selectWorker(i);
        return true;
    }
}
