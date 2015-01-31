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
    private static Activity activity;
    private static MainController mainController;

    public static void create(Activity activity, MainController mainController) {
        UiFactory.activity = activity;
        UiFactory.mainController = mainController;

        MenuFactory.create(mainController);
        MessageFactory.create(mainController);
    }

    public static View getViewForEntity(Entity entity) {
        Menu menu = MenuFactory.getMenu(entity);
        Message message = MessageFactory.getMessage(entity);
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

    public static View getCountryMenuView() {
        return new MenuView(activity,
                MenuFactory.getCountryMenu(), getGameController(), mainController);
    }

    public static View getWorkersMenuView() {
        return new MenuView(activity,
                MenuFactory.getWorkersMenu(), getGameController(), mainController);
    }


    public static View getMapView() {
        return new MapView(activity, getGameController(), mainController);
    }

    public static View getArmyView() {
        return new ArmyView(activity, getGameController(), mainController);
    }

    private static GameController getGameController() {
        return mainController.getGameController();
    }
}
