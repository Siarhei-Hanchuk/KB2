package com.neschur.kb2.app.views;

import android.view.SurfaceView;

import com.neschur.kb2.app.controllers.ArmyShopViewController;
import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.MagicViewController;
import com.neschur.kb2.app.controllers.MainMenuController;
import com.neschur.kb2.app.controllers.MainViewController;
import com.neschur.kb2.app.controllers.PlayerViewsController;
import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.entities.ArmyShop;
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
        if (entity instanceof ArmyShop) {
            view = new ArmyShopView((ArmyShopViewController)controller, (ArmyShop) entity);
        }
        return view;
    }

    public SurfaceView getCountryMenuView() {
        return new MenuView(controller, menuFactory.getCountryMenu());
    }

    public static SurfaceView getWorkersMenuView(ViewController controller) {
        return new MenuView(controller, (new MenuFactory(controller)).getWorkersMenu());
    }

    public static SurfaceView getMapView(PlayerViewsController controller) {
        return new MapView(controller);
    }

    public static SurfaceView getArmyView(PlayerViewsController controller) {
        return new ArmyView(controller);
    }

    public static SurfaceView getMagicView(MagicViewController controller) {
        return new MagicView(controller);
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
