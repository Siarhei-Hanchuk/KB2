package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.models.BattleField;
import com.neschur.kb2.app.models.MapPointBattle;
import com.neschur.kb2.app.models.Player;

public class BattleController {
    private BattleField battleField;

    public BattleController(Player player, Fighting fighting) {
        this.battleField = new BattleField(player, fighting);
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
}
