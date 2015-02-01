package com.neschur.kb2.app.views;

import android.view.SurfaceView;

import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.MainMenuController;
import com.neschur.kb2.app.controllers.MainViewController;
import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.ui.menus.Menu;
import com.neschur.kb2.app.ui.menus.MenuFactory;
import com.neschur.kb2.app.ui.messages.Message;
import com.neschur.kb2.app.ui.messages.MessageFactory;

public class ViewFactory {
    private ViewController controller;
    private MenuFactory menuFactory;
    private MessageFactory messageFactory;

    public ViewFactory(ViewController controller) {
        this.controller = controller;

        this.menuFactory = new MenuFactory(controller);
        this.messageFactory = new MessageFactory(controller);
    }

    public SurfaceView getViewForEntity(Entity entity) {
        Menu menu = menuFactory.getMenu(entity);
        Message message = messageFactory.getMessage(entity);
        View view = null;
        if (menu != null) {
            view = new MenuView(controller, menu);
        }
        if (message != null) {
            view = new MessageView(controller, message);
        }
//        if (entity instanceof ArmyShop) {
//            view = new ArmyShopView(controller, (ArmyShop) entity);
//        }
        return view;
    }

    public SurfaceView getCountryMenuView() {
        return new MenuView(controller, menuFactory.getCountryMenu());
    }

    public SurfaceView getWorkersMenuView() {
        return new MenuView(controller, menuFactory.getWorkersMenu());
    }


    public SurfaceView getMapView() {
        return null;//new MapView(mainController);
    }

    public SurfaceView getArmyView() {
        return null;//new ArmyView(mainController);
    }

    public SurfaceView getMagicView() {
        return null;//new MagicView(mainController);
    }

    public static SurfaceView getMainMenuView(MainMenuController controller) {
        return new MainMenuView(controller);
    }

    public static SurfaceView getMainView(MainViewController controller) {
        return new MainView(controller);
    }

    public static SurfaceView getBattleView(BattleController controller) {
        return new BattleView(controller);
    }
}
