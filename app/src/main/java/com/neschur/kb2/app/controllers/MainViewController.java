package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.models.GameGrid;

public interface MainViewController extends ViewController {
    GameGrid getGameGrid();

    void touchMenu(int item);

    void touchUp();

    void touchUpLeft();

    void touchUpRight();

    void touchDown();

    void touchDownLeft();

    void touchDownRight();

    void touchRight();

    void touchLeft();

    boolean isTrainingMode();
}
