package com.neschur.kb2.app.views;

import com.neschur.kb2.app.controllers.MainController;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.ui.menus.Menu;
import com.neschur.kb2.app.ui.menus.MenuFactory;
import com.neschur.kb2.app.ui.messages.Message;
import com.neschur.kb2.app.ui.messages.MessageFactory;
import com.neschur.kb2.app.views.ArmyShopView;
import com.neschur.kb2.app.views.ArmyView;
import com.neschur.kb2.app.views.MagicView;
import com.neschur.kb2.app.views.MapView;
import com.neschur.kb2.app.views.MenuView;
import com.neschur.kb2.app.views.MessageView;
import com.neschur.kb2.app.views.View;

public class ViewFactory {
    private final MainController mainController;
    private final MenuFactory menuFactory;
    private final MessageFactory messageFactory;

    public ViewFactory(MainController mainController) {
        this.mainController = mainController;

        this.menuFactory = new MenuFactory(mainController);
        this.messageFactory = new MessageFactory(mainController);
    }

    public View getViewForEntity(Entity entity) {
        Menu menu = menuFactory .getMenu(entity);
        Message message = messageFactory.getMessage(entity);
        View view = null;
        if (menu != null) {
            view = new MenuView(mainController, menu);
        }
        if (message != null) {
            view = new MessageView(mainController, message);
        }
        if (entity instanceof ArmyShop) {
            view = new ArmyShopView(mainController, (ArmyShop) entity,
                    mainController.getGameController());
        }
        return view;
    }

    public View getCountryMenuView() {
        return new MenuView(mainController, menuFactory.getCountryMenu());
    }

    public View getWorkersMenuView() {
        return new MenuView(mainController, menuFactory.getWorkersMenu());
    }


    public View getMapView() {
        return new MapView(mainController);
    }

    public View getArmyView() {
        return new ArmyView(mainController);
    }

    public View getMagicView() {
        return new MagicView(mainController);
    }
}
