package com.neschur.kb2.app.controllers.implementations;

import android.app.Activity;

import com.neschur.kb2.app.controllers.ApplicationController;
import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.models.battle.BattleField;
import com.neschur.kb2.app.models.battle.MapPointBattle;
import com.neschur.kb2.app.views.BattleView;
import com.neschur.kb2.app.views.ViewFactory;

public class BattleControllerImpl extends ApplicationController implements BattleController {
    private final BattleField battleField;
    private final BattleView battleView;

    public BattleControllerImpl(Activity activity, ViewController controller, Fighting fighting) {
        super(activity);
        this.battleField = new BattleField(controller.getGame().getPlayer(), fighting, this);
        battleView = ViewFactory.getBattleView(this);
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
        setContentView(ViewFactory.getViewBattleMessageView(this, win));
    }

    @Override
    public void updateView() {
        battleView.refresh();
    }

    @Override
    public void viewClose() {
        new MainViewControllerImpl(activity);
    }
}
