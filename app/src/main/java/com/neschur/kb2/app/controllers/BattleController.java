package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.models.battle.MapPointBattle;

public interface BattleController extends ViewController {
    MapPointBattle[][] getMap();

    void select(int x, int y);

    int getSelectedX();

    int getSelectedY();

    void battleFinish(boolean win);

    void updateView();

    void updateView(int delay);
}
