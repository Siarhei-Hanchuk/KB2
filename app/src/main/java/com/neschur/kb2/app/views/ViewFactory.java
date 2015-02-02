package com.neschur.kb2.app.views;

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

    public static View getWorkersMenuView(ViewController controller) {
        return new MenuView(controller, getMenuFactory(controller).getWorkersMenu());
    }

    public static View getMapView(PlayerViewsController controller) {
        return new MapView(controller);
    }

    public static View getArmyView(PlayerViewsController controller) {
        return new ArmyView(controller);
    }

    public static View getMagicView(MagicViewController controller) {
        return new MagicView(controller);
    }

    public static View getMainMenuView(MainMenuController controller) {
        return new MainMenuView(controller);
    }

    public static View getMainView(MainViewController controller) {
        return new MainView(controller);
    }

    public static BattleView getBattleView(BattleController controller) {
        return new BattleView(controller);
    }

    public static View getCountryMenuView(ViewController controller) {
        return new MenuView(controller, getMenuFactory(controller).getCountryMenu());
    }

    public static View getViewForEntity(ViewController controller, Entity entity) {
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

    public static View getViewBattleMessageView(ViewController controller, boolean result) {
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
