package com.neschur.kb2.app.ui;

import android.app.Activity;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.controllers.MainController;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.ui.menus.Menu;
import com.neschur.kb2.app.ui.menus.MenuFactory;
import com.neschur.kb2.app.ui.messages.Message;
import com.neschur.kb2.app.ui.messages.MessageFactory;
import com.neschur.kb2.app.views.ArmyShopView;
import com.neschur.kb2.app.views.ArmyView;
import com.neschur.kb2.app.views.MapView;
import com.neschur.kb2.app.views.MenuView;
import com.neschur.kb2.app.views.MessageView;
import com.neschur.kb2.app.views.View;

public class UiFactory {
    private final Activity activity;
    private final MainController mainController;
    private final MenuFactory menuFactory;
    private final MessageFactory messageFactory;

    public UiFactory(Activity activity, MainController mainController) {
        this.activity = activity;
        this.mainController = mainController;

        this.menuFactory = new MenuFactory(mainController);
        this.messageFactory = new MessageFactory(mainController);
    }

    public View getViewForEntity(Entity entity) {
        Menu menu = menuFactory .getMenu(entity);
        Message message = messageFactory.getMessage(entity);
        View view = null;
        if (menu != null) {
            view = new MenuView(activity, menu, getGameController(), mainController);
        }
        if (message != null) {
            view = new MessageView(activity, message, getGameController(), mainController);
        }
        if (entity instanceof ArmyShop) {
            view = new ArmyShopView(activity, (ArmyShop) entity, getGameController(), mainController);
        }
        return view;
    }

    public View getCountryMenuView() {
        return new MenuView(activity,
                menuFactory.getCountryMenu(), getGameController(), mainController);
    }

    public View getWorkersMenuView() {
        return new MenuView(activity,
                menuFactory.getWorkersMenu(), getGameController(), mainController);
    }


    public View getMapView() {
        return new MapView(activity, getGameController(), mainController);
    }

    public View getArmyView() {
        return new ArmyView(activity, getGameController(), mainController);
    }

    private GameController getGameController() {
        return mainController.getGameController();
    }
}
