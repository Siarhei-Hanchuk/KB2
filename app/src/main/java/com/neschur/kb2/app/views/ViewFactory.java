package com.neschur.kb2.app.views;

import android.view.SurfaceView;

import com.neschur.kb2.app.controllers.ArmyShopViewController;
import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.GameOwner;
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
    private static MenuFactory menuFactory;
    private static MessageFactory messageFactory;

    public static SurfaceView getWorkersMenuView(ViewController controller) {
        return new MenuView(controller, getMenuFactory(controller).getWorkersMenu());
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

    public static SurfaceView getCountryMenuView(ViewController controller) {
        return new MenuView(controller, getMenuFactory(controller).getCountryMenu());
    }

    public static SurfaceView getViewForEntity(ViewController controller, Entity entity) {
        Menu menu = getMenuFactory(controller).getMenu(entity);
        Message message = getMessageFactory(controller).getMessage(entity);
        View view = null;
        if (menu != null) {
            view = new MenuView(controller, menu);
        }
        if (message != null) {
            view = new MessageView(controller, message);
        }
        if (entity instanceof ArmyShop) {
            view = new ArmyShopView((ArmyShopViewController) controller, (ArmyShop) entity);
        }
        return view;
    }

    public static SurfaceView getViewBattleMessageView(ViewController controller, boolean result) {
        return new MessageView(controller, getMessageFactory(controller).getBattleMessage(result));
    }

    private static MenuFactory getMenuFactory(GameOwner controller) {
        if (menuFactory == null) {
            menuFactory = new MenuFactory(controller);
        }
        return menuFactory;
    }

    private static MessageFactory getMessageFactory(GameOwner controller) {
        if (messageFactory == null) {
            messageFactory = new MessageFactory(controller);
        }
        return messageFactory;
    }
}
