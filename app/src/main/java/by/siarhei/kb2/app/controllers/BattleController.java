package by.siarhei.kb2.app.controllers;

import java.util.HashMap;
import by.siarhei.kb2.app.server.models.battle.MapPointBattle;

public interface BattleController extends ViewController {
    MapPointBattle[][] getMap();

    void select(int x, int y);

    int getSelectedX();

    int getSelectedY();

    void battleFinish(boolean win, HashMap<String, Integer> casualties);

    void updateView();

    void updateView(int delay);
}
