package com.neschur.kb2.app;

import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.MagicViewController;
import com.neschur.kb2.app.controllers.MainMenuController;
import com.neschur.kb2.app.controllers.MainViewController;
import com.neschur.kb2.app.controllers.PlayerViewsController;
import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.View;

public interface ViewFactory {
    public View getWorkersMenuView(ViewController controller);

    public View getPlayersView(MagicViewController controller, String viewName);

    public View getMainMenuView(MainMenuController controller);

    public View getMainView(MainViewController controller);

    public View getBattleView(BattleController controller);

    public View getCountryMenuView(ViewController controller);

    public View getViewForEntity(ViewController controller, Entity entity);

    public View getViewBattleMessageView(ViewController controller, boolean result,
                                         int authority, int money);

    public View getWeekEndView(ViewController controller, String armyTextId, String city);
}
