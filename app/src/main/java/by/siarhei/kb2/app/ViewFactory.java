package by.siarhei.kb2.app;

import java.util.HashMap;
import by.siarhei.kb2.app.controllers.BattleController;
import by.siarhei.kb2.app.controllers.MagicViewController;
import by.siarhei.kb2.app.controllers.MainMenuController;
import by.siarhei.kb2.app.controllers.ViewController;
import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.entities.Entity;

public interface ViewFactory {
    View getBattleView(BattleController controller);

    View getViewBattleResultsView(ViewController viewController,
                                  HashMap<String, Integer> casualties, boolean result, int authority, int money);
}
