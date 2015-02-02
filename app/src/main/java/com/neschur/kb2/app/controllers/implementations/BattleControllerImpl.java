package com.neschur.kb2.app.controllers.implementations;

import com.neschur.kb2.app.controllers.ApplicationController;
import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.models.battle.BattleField;
import com.neschur.kb2.app.models.battle.MapPointBattle;
import com.neschur.kb2.app.views.BattleView;

public class BattleControllerImpl extends ApplicationController implements BattleController {
    private final BattleField battleField;
    private final BattleView battleView;

    public BattleControllerImpl(ViewController controller, Fighting fighting) {
        this.battleField = new BattleField(controller.getGame().getPlayer(), fighting, this);
        battleView = getViewFactory().getBattleView(this);
        setContentView(battleView);
    }

    @Override
    public MapPointBattle[][] getMap() {
        return battleField.getMapPoints();
    }

    @Override
    public void select(int x, int y) {
        battleField.select(x, y);
        updateView();
    }

    @Override
    public int getSelectedX() {
        return battleField.getSelectedX();
    }

    @Override
    public int getSelectedY() {
        return battleField.getSelectedY();
    }

    @Override
    public void battleFinish(boolean win) {
        setContentView(getViewFactory().getViewBattleMessageView(this, win));
    }

    @Override
    public void updateView() {
        battleView.refresh();
    }

    @Override
    public void viewClose() {
        new MainViewControllerImpl();
    }
}
