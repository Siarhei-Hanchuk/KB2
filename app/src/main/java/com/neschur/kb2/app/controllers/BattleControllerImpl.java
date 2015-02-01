package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.models.battle.BattleField;
import com.neschur.kb2.app.models.battle.BattleFinishing;
import com.neschur.kb2.app.models.battle.MapPointBattle;

public class BattleControllerImpl implements BattleController, BattleFinishing {
    private BattleField battleField;
    private BattleFinishing mainController;

    public BattleControllerImpl(BattleFinishing mainController, Player player, Fighting fighting) {
        this.battleField = new BattleField(player, fighting, this);
        this.mainController = mainController;
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
        mainController.battleFinish(win);
    }
}
