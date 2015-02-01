package com.neschur.kb2.app.controllers;

public interface MainMenuController extends ViewController {
    public void exit();

    public void saveGame();

    public void loadGame();

    public void newGame();

    public void newTraining();

    public boolean isCurrentGame();
}
