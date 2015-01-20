package com.neschur.kb2.app.ui;

import android.app.Activity;
import android.view.View;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.controllers.MainController;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.ui.menus.CountryMenu;
import com.neschur.kb2.app.ui.menus.Menu;
import com.neschur.kb2.app.ui.messages.Message;
import com.neschur.kb2.app.views.ArmyShopView;
import com.neschur.kb2.app.views.ArmyView;
import com.neschur.kb2.app.views.MapView;
import com.neschur.kb2.app.views.MenuView;
import com.neschur.kb2.app.views.MessageView;

public class UiFactory {
    private static Activity activity;
    private static GameController gameController;
    private static MainController mainController;

    public static void create(Activity activity, MainController mainController) {
        UiFactory.activity = activity;
        UiFactory.gameController = mainController.getGameController();
        UiFactory.mainController = mainController;

        MenuFactory.create(activity, gameController);
        MessageFactory.create(activity, gameController);
    }

    public static View getViewForEntity(Entity entity) {
        Menu menu = MenuFactory.getMenu(entity);
        Message message = MessageFactory.getMessage(entity);
        View view = null;
        if (menu != null) {
            view = new MenuView(activity, menu, gameController, mainController);
        }
        if (message != null) {
            view = new MessageView(activity, message, gameController, mainController);
        }
        if (entity instanceof ArmyShop) {
            view = new ArmyShopView(activity, (ArmyShop) entity, gameController, mainController);
        }
        return view;
    }

    public static View getMenuView() {
        return new MenuView(activity,
                new CountryMenu(activity, gameController), gameController, mainController);
    }

    public static View getMapView() {
        return new MapView(activity, gameController, mainController);
    }

    public static View getArmyView() {
        return new ArmyView(activity, gameController, mainController);
    }
}
