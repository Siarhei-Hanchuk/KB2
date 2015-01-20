package com.neschur.kb2.app.ui.menus;

import android.app.Activity;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;

/**
 * Created by siarhei on 20.1.15.
 */
public class CaptainMenu extends Menu{
    public CaptainMenu(Activity activity, Entity entity, GameController gameController) {
        super(activity, gameController);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public String getItemDescription(int i) {
        switch (i) {
            case 0:
                return "Yes";
            case 1:
                return "No";
        }
        return null;
    }

    @Override
    public boolean select(int i) {
        switch (i) {
            case 0:
                gameController.activateBattle();
                return true;
            case 1:
                return true;
        }
        return false;
    }
}
