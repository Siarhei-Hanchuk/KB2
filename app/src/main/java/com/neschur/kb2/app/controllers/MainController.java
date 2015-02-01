package com.neschur.kb2.app.controllers;

import android.app.Activity;
import android.view.SurfaceView;

import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.views.ViewFactory;
import com.neschur.kb2.app.warriors.WarriorFactory;

public class MainController extends ApplicationController implements
        PlayerViewsController, ArmyShopViewController, MagicViewController, ActivateCallback {
    private SurfaceView mainView;
    private ViewFactory viewFactory;
    private ViewController currentController;

    public MainController(Activity activity) {
        super(activity);

        currentController = new MainMenuControllerImpl(activity);
        viewFactory = new ViewFactory(this);
    }

    public void activateEntity(Entity entity) {
        SurfaceView view = viewFactory.getViewForEntity(entity);
        if (view != null)
            setContentView(view);
    }

    @Override
    public void viewClose() {
        setContentView(mainView);
    }

    public void activateBattle(Fighting fighting) {
        new BattleControllerImpl(activity, this, fighting);
    }

    public void activateMainMenu() {
        if (!(currentController instanceof MainController))
            currentController = new MainMenuControllerImpl(activity);
    }


    @Override
    public Player getPlayer() {
        return getGameController().getPlayer();
    }

    @Override
    public void buyArmy(ArmyShop shop, int count) {
        getGameController().buyArmy(shop, count);
    }

    @Override
    public void takeArmy(String id) {
        getPlayer().pushArmy(WarriorFactory.create(id), 1);
    }

    public void setMainView(SurfaceView view) {
        mainView = view;
        setContentView(view);
    }
}
