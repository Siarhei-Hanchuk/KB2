package com.neschur.kb2.app.controllers.implementations;

import android.app.Activity;
import android.view.SurfaceView;

import com.neschur.kb2.app.controllers.ApplicationController;
import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.GameOwner;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.models.battle.BattleField;
import com.neschur.kb2.app.models.battle.MapPointBattle;
import com.neschur.kb2.app.views.ViewFactory;

public class BattleControllerImpl extends ApplicationController implements BattleController {
    private BattleField battleField;

    public BattleControllerImpl(Activity activity, GameOwner controller, Fighting fighting) {
        super(activity);
        this.battleField = new BattleField(controller.getGameController().getPlayer(), fighting, this);
        SurfaceView battleView = ViewFactory.getBattleView(this);
        setContentView(battleView);
    }

    public MapPointBattle[][] getMap() {
        return battleField.getMapPoints();
    }

    public void select(int x, int y) {
        battleField.select(x, y);
    }

    public int getSelectedX() {
        return battleField.getSelectedX();
    }

    public int getSelectedY() {
        return battleField.getSelectedY();
    }

    @Override
    public void battleFinish(boolean win) {

    }

    @Override
    public void viewClose() {

    }
}
