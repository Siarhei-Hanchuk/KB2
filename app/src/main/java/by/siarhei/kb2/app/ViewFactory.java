package by.siarhei.kb2.app;

import by.siarhei.kb2.app.controllers.BattleController;
import by.siarhei.kb2.app.controllers.MagicViewController;
import by.siarhei.kb2.app.controllers.MainMenuController;
import by.siarhei.kb2.app.controllers.MainViewController;
import by.siarhei.kb2.app.controllers.ViewController;
import by.siarhei.kb2.app.entities.City;
import by.siarhei.kb2.app.entities.Entity;

public interface ViewFactory {
    View getWorkersMenuView(ViewController controller);

    View getPlayersView(MagicViewController controller, String viewName);

    View getMainMenuView(MainMenuController controller);

    View getMainView(MainViewController controller);

    View getBattleView(BattleController controller);

    View getCountryMenuView(ViewController controller);

    View getViewForEntity(ViewController controller, Entity entity);

    View getViewBattleMessageView(ViewController controller, boolean result,
                                  int authority, int money);

    View getWeekEndView(ViewController controller, String armyTextId, City city);
}
