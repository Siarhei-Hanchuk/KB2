package com.neschur.kb2.app.controllers.implementations;

import com.neschur.kb2.app.controllers.ApplicationController;
import com.neschur.kb2.app.controllers.ArmyShopViewController;
import com.neschur.kb2.app.controllers.MagicViewController;
import com.neschur.kb2.app.controllers.PlayerViewsController;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.views.View;
import com.neschur.kb2.app.views.ViewFactory;
import com.neschur.kb2.app.warriors.WarriorFactory;

public class PlayerViewsControllerImpl extends ApplicationController
        implements PlayerViewsController, MagicViewController, ArmyShopViewController {
    private View view;
    public PlayerViewsControllerImpl(Object viewType) {
        if (viewType instanceof String) {
            switch ((String) viewType) {
                case "magic":
                    view = ViewFactory.getMagicView(this);
                    break;
                case "army":
                    view = ViewFactory.getArmyView(this);
                    break;
                case "map":
                    view = ViewFactory.getMapView(this);
                    break;
            }
        } else {
            view = ViewFactory.getViewForEntity(this, (Entity) viewType);
        }
        setContentView(view);
    }

    @Override
    public Player getPlayer() {
        return getGame().getPlayer();
    }

    @Override
    public void viewClose() {
        new MainViewControllerImpl();
    }

    @Override
    public void takeArmy(String id) {
        getPlayer().pushArmy(WarriorFactory.create(id), 1);
    }

    @Override
    public void buyArmy(ArmyShop shop, int count) {
        getGame().buyArmy(shop, count);
        view.refresh();
    }
}
