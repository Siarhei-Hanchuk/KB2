package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;

import com.neschur.kb2.app.ViewFactory;
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

public class ViewFactoryImpl implements ViewFactory {
    private final MenuFactory menuFactory;
    private final MessageFactory messageFactory;
    private final Context context;

    public ViewFactoryImpl(Context context, GameOwner gameOwner) {
        this.context = context;
        menuFactory = new MenuFactory(gameOwner);
        messageFactory = new MessageFactory(gameOwner);
    }

    public ViewImpl getWorkersMenuView(ViewController controller) {
        return new MenuView(context, controller, menuFactory.getWorkersMenu());
    }

    public ViewImpl getMapView(PlayerViewsController controller) {
        return new MapView(context, controller);
    }

    public ViewImpl getArmyView(PlayerViewsController controller) {
        return new ArmyView(context, controller);
    }

    public ViewImpl getMagicView(MagicViewController controller) {
        return new MagicView(context, controller);
    }

    public ViewImpl getMainMenuView(MainMenuController controller) {
        return new MainMenuView(context, controller);
    }

    public ViewImpl getMainView(MainViewController controller) {
        return new MainView(context, controller);
    }

    public BattleView getBattleView(BattleController controller) {
        return new BattleView(context, controller);
    }

    public ViewImpl getCountryMenuView(ViewController controller) {
        return new MenuView(context, controller, menuFactory.getCountryMenu());
    }

    public ViewImpl getViewForEntity(ViewController controller, Entity entity) {
        Menu menu = menuFactory.getMenu(entity);
        Message message = messageFactory.getMessage(entity);
        ViewImpl view = null;
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

    public ViewImpl getViewBattleMessageView(ViewController controller,
                                             boolean result, int authority, int money) {
        return new MessageView(context, controller,
                messageFactory.getBattleMessage(result, authority, money));
    }
}
