package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.models.battle.MapPointBattle;

public interface BattleController {
    public MapPointBattle[][] getMap();
    public void select(int x, int y);
    public int getSelectedX();
    public int getSelectedY();
}
