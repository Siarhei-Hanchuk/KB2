package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;

import com.neschur.kb2.app.View;
import com.neschur.kb2.app.ViewFactory;
import com.neschur.kb2.app.controllers.ArmyShopViewController;
import com.neschur.kb2.app.controllers.BattleAskController;
import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.GameOwner;
import com.neschur.kb2.app.controllers.MagicViewController;
import com.neschur.kb2.app.controllers.MainMenuController;
import com.neschur.kb2.app.controllers.MainViewController;
import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.Fighting;
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

    @Override
    public ViewImpl getWorkersMenuView(ViewController controller) {
        return new MenuView(context, controller, menuFactory.getWorkersMenu());
    }

    @Override
    public View getPlayersView(MagicViewController controller, String viewName) {
        switch (viewName) {
            case "magic":
                return new MagicView(context, controller);
            case "army":
                return new ArmyView(context, controller);
            case "map":
                return new MapView(context, controller);
            case "status":
                return new StatusView(context, controller);
        }
        return null;
    }

    @Override
    public ViewImpl getMainMenuView(MainMenuController controller) {
        return new MainMenuView(context, controller);
    }

    @Override
    public ViewImpl getMainView(MainViewController controller) {
        return new MainView(context, controller);
    }

    @Override
    public BattleView getBattleView(BattleController controller) {
        return new BattleView(context, controller);
    }

    @Override
    public ViewImpl getCountryMenuView(ViewController controller) {
        return new MenuView(context, controller, menuFactory.getCountryMenu());
    }

    @Override
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
        if (entity instanceof Fighting) {
            view = new BattleAskView(context, (BattleAskController) controller, (Fighting) entity);
        }
        return view;
    }

    @Override
    public ViewImpl getViewBattleMessageView(ViewController controller,
                                             boolean result, int authority, int money) {
        return new MessageView(context, controller,
                messageFactory.getBattleMessage(result, authority, money));
    }

    @Override
    public View getWeekEndView(ViewController controller, String armyTextId, City city) {
        return new WeekEndView(context, controller, armyTextId, city);
    }
}
