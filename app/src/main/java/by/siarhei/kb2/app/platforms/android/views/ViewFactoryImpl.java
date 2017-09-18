package by.siarhei.kb2.app.platforms.android.views;

import java.util.HashMap;
import android.content.Context;
import by.siarhei.kb2.app.View;
import by.siarhei.kb2.app.ViewFactory;
import by.siarhei.kb2.app.controllers.ArmyShopViewController;
import by.siarhei.kb2.app.controllers.BattleAskController;
import by.siarhei.kb2.app.controllers.BattleController;
import by.siarhei.kb2.app.controllers.GameOwner;
import by.siarhei.kb2.app.controllers.MagicViewController;
import by.siarhei.kb2.app.controllers.MainMenuController;
import by.siarhei.kb2.app.controllers.MainViewController;
import by.siarhei.kb2.app.controllers.ViewController;
import by.siarhei.kb2.app.entities.ArmyShop;
import by.siarhei.kb2.app.entities.City;
import by.siarhei.kb2.app.entities.Entity;
import by.siarhei.kb2.app.entities.Fighting;
import by.siarhei.kb2.app.platforms.android.MainActivity;
import by.siarhei.kb2.app.ui.menus.CityMenu;
import by.siarhei.kb2.app.ui.menus.GoldChestMenu;
import by.siarhei.kb2.app.ui.menus.Menu;
import by.siarhei.kb2.app.ui.menus.MenuFactory;
import by.siarhei.kb2.app.ui.messages.Message;
import by.siarhei.kb2.app.ui.messages.MessageFactory;

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
            if(menu instanceof GoldChestMenu)
                view = new Menu2View(context, controller, menu);
            else {
                view = new MenuView(context, controller, menu);
                if (menu instanceof CityMenu) {
                    MainActivity.showToast(((CityMenu) menu).getCityName());
                }
            }

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
    public ViewImpl getViewBattleResultsView(ViewController viewController,
                                             HashMap<String, Integer> casualties, boolean result, int authority, int money) {
        return new BattleResultsView(context, viewController,
                messageFactory.getBattleMessage(result, authority, money), casualties);
    }

    @Override
    public View getWeekEndView(ViewController controller, String armyTextId, City city) {
        return new WeekEndView(context, controller, armyTextId, city);
    }
}
