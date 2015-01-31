package com.neschur.kb2.app.ui.menus;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Captain;
import com.neschur.kb2.app.entities.Entity;

public class CaptainMenu extends Menu {
    private final Captain captain;

    CaptainMenu(Entity entity, GameController gameController) {
        super(gameController);
        this.captain = (Captain) entity;
    }

    @Override
    public int getCount() {
        if (player.noArmy()) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public String getItemDescription(int i) {
        if (player.noArmy()) {
            return I18n.translate(R.string.entity_captain_noArmy);
        } else {
            switch (i) {
                case 0:
                    return "Yes";
                case 1:
                    return "No";
            }
        }
        return null;
    }

    @Override
    public boolean select(int i) {
        if (player.noArmy()) {
            return false;
        } else {
            switch (i) {
                case 0:
                    gameController.activateBattle(captain);
                    return true;
                case 1:
                    return true;
            }
        }
        return false;
    }
}
