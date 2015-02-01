package com.neschur.kb2.app.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.Surface;
import android.view.SurfaceView;

import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.models.battle.BattleField;
import com.neschur.kb2.app.models.battle.BattleFinishing;
import com.neschur.kb2.app.models.battle.MapPointBattle;
import com.neschur.kb2.app.views.ViewFactory;

public class BattleControllerImpl extends ApplicationController implements BattleController {
    private BattleField battleField;
    private MainController mainController;

    public BattleControllerImpl(Activity activity, MainController mainController, Fighting fighting) {
        super(activity);
        this.battleField = new BattleField(mainController.getPlayer(), fighting, this);
        this.mainController = mainController;
        SurfaceView battleView = ViewFactory.getBattleView(this);
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
