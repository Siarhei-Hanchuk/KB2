package com.neschur.kb2.app.views;

import android.content.Context;

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
    private final MenuFactory menuFactory;
    private final MessageFactory messageFactory;
    private final Context context;

    public ViewFactory(Context context, GameOwner gameOwner) {
        this.context = context;
        menuFactory = new MenuFactory(gameOwner);
        messageFactory = new MessageFactory(gameOwner);
    }

    public View getWorkersMenuView(ViewController controller) {
        return new MenuView(context, controller, menuFactory.getWorkersMenu());
    }

    public View getMapView(PlayerViewsController controller) {
        return new MapView(context, controller);
    }

    public View getArmyView(PlayerViewsController controller) {
        return new ArmyView(context, controller);
    }

    public View getMagicView(MagicViewController controller) {
        return new MagicView(context, controller);
    }

    public View getMainMenuView(MainMenuController controller) {
        return new MainMenuView(context, controller);
    }

    public View getMainView(MainViewController controller) {
        return new MainView(context, controller);
    }

    public BattleView getBattleView(BattleController controller) {
        return new BattleView(context, controller);
    }

    public View getCountryMenuView(ViewController controller) {
        return new MenuView(context, controller, menuFactory.getCountryMenu());
    }

    public View getViewForEntity(ViewController controller, Entity entity) {
        Menu menu = menuFactory.getMenu(entity);
        Message message = messageFactory.getMessage(entity);
        View view = null;
        if (menu != null) {
            view = new MenuView(context, controller, menu);
        }
        if (message != null) {
            view = new MessageView(context, controller, message);
        }
        if (entity instanceof ArmyShop) {
            view = new ArmyShopView(context, (ArmyShopViewController) controller, (ArmyShop) entity);
        }
        return view;
    }

    public View getViewBattleMessageView(ViewController controller, boolean result) {
        return new MessageView(context, controller, messageFactory.getBattleMessage(result));
    }
}
