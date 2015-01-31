package com.neschur.kb2.app.ui.menus;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.controllers.GameController;

public class WorkersMenu extends Menu {
    WorkersMenu(GameController gameController) {
        super(gameController);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public String getItemDescription(int i) {
        return I18n.translate("workers_item" + (i + 1)) + ": " + player.getWorker(i);
    }

    @Override
    public boolean select(int i) {
        gameController.selectWorker(i);
        return true;
    }
}
