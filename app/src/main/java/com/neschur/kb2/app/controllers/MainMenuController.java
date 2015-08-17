package com.neschur.kb2.app.controllers;

public interface MainMenuController extends ViewController {
    void exit();

    void saveGame();

    void loadGame();

    void newGame();

    void newTraining();

    boolean isCurrentGame();

    void newTestGame();
}
