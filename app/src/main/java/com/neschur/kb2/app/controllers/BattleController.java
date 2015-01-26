package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.entities.WarriorEntity;
import com.neschur.kb2.app.models.BattleField;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.Player;

public class BattleController {
    private BattleField battleField;
//    private Player player;
//    private Fighting fighting;

    public BattleController(Player player, Fighting fighting) {
//        this.player = player;
//        this.fighting = fighting;
        this.battleField = new BattleField(player, fighting);
    }

    public MapPoint[][] getMap() {
        return battleField.getMapPoints();
    }

    public void select(int x, int y) {
        WarriorEntity entity = (WarriorEntity)battleField.getMapPoint(x, y).getEntity();
        if (entity != null) {
            
        }
    }

//    public MapPoint[][] getMap() {
//        return battleField.getMapPoint();
//    }
}
