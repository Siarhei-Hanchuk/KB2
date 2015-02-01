package com.neschur.kb2.app.views;

import android.view.SurfaceView;

import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.MainController;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.ui.menus.Menu;
import com.neschur.kb2.app.ui.menus.MenuFactory;
import com.neschur.kb2.app.ui.messages.Message;
import com.neschur.kb2.app.ui.messages.MessageFactory;

public class ViewFactory {
    private final MainController mainController;
    private final MenuFactory menuFactory;
    private final MessageFactory messageFactory;

    public ViewFactory(MainController mainController) {
        this.mainController = mainController;

        this.menuFactory = new MenuFactory(mainController);
        this.messageFactory = new MessageFactory(mainController);
    }

    public SurfaceView getViewForEntity(Entity entity) {
        Menu menu = menuFactory.getMenu(entity);
        Message message = messageFactory.getMessage(entity);
        View view = null;
        if (menu != null) {
            view = new MenuView(mainController, menu);
        }
        if (message != null) {
            view = new MessageView(mainController, message);
        }
        if (entity instanceof ArmyShop) {
            view = new ArmyShopView(mainController, (ArmyShop) entity);
        }
        return view;
    }

    public SurfaceView getCountryMenuView() {
        return new MenuView(mainController, menuFactory.getCountryMenu());
    }

    public SurfaceView getWorkersMenuView() {
        return new MenuView(mainController, menuFactory.getWorkersMenu());
    }


    public SurfaceView getMapView() {
        return new MapView(mainController);
    }

    public SurfaceView getArmyView() {
        return new ArmyView(mainController);
    }

    public SurfaceView getMagicView() {
        return new MagicView(mainController);
    }

    public SurfaceView getMainMenuView() {
        return new MainMenuView(mainController);
    }

    public SurfaceView getMainView() {
        return new MainView(mainController);
    }

    public SurfaceView getBattleView(BattleController battleController) {
        return new BattleView(mainController, battleController);
    }
}
