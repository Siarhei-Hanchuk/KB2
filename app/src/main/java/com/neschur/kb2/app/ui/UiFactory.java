package com.neschur.kb2.app.ui;

import android.app.Activity;

import com.neschur.kb2.app.controllers.MainController;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Player;
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
            view = new MenuView(activity, menu, mainController);
        }
        if (message != null) {
            view = new MessageView(activity, message, mainController);
        }
        if (entity instanceof ArmyShop) {
            view = new ArmyShopView(activity, (ArmyShop) entity, getPlayer(),
                    mainController.getGameController(), mainController);
        }
        return view;
    }

    public View getCountryMenuView() {
        return new MenuView(activity,
                menuFactory.getCountryMenu(), mainController);
    }

    public View getWorkersMenuView() {
        return new MenuView(activity,
                menuFactory.getWorkersMenu(), mainController);
    }


    public View getMapView() {
        return new MapView(activity, getPlayer(), mainController);
    }

    public View getArmyView() {
        return new ArmyView(activity, getPlayer(), mainController);
    }

    private Player getPlayer() {
        return mainController.getGameController().getPlayer();
    }
}
