package com.neschur.kb2.app.controllers.implementations;

import android.app.Activity;
import android.view.SurfaceView;

import com.neschur.kb2.app.controllers.ApplicationController;
import com.neschur.kb2.app.controllers.ArmyShopViewController;
import com.neschur.kb2.app.controllers.MagicViewController;
import com.neschur.kb2.app.controllers.PlayerViewsController;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.views.ViewFactory;
import com.neschur.kb2.app.warriors.WarriorFactory;

public class PlayerViewsControllerImpl extends ApplicationController
        implements PlayerViewsController, MagicViewController, ArmyShopViewController {
    public PlayerViewsControllerImpl(Activity activity, Object viewType) {
        super(activity);
        if (viewType instanceof String ) {
            switch ((String) viewType) {
                case "magic":
                    setContentView(ViewFactory.getMagicView(this));
                    break;
                case "army":
                    setContentView(ViewFactory.getArmyView(this));
                    break;
                case "map":
                    setContentView(ViewFactory.getMapView(this));
                    break;
            }
        } else {
            setContentView(ViewFactory.getViewForEntity(this, (Entity)viewType));
        }
    }

    @Override
    public Player getPlayer() {
        return getGameController().getPlayer();
    }

    @Override
    public void viewClose() {
        new MainViewControllerImpl(activity);
    }

    @Override
    public void takeArmy(String id) {
        getPlayer().pushArmy(WarriorFactory.create(id), 1);
    }

    @Override
    public void buyArmy(ArmyShop shop, int count) {
        getGameController().buyArmy(shop, count);
    }
}
