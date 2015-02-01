package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.Storage;

public interface MainMenuController extends ViewController {
    public void exit();
    public void saveGame();
    public void loadGame();
    public void newGame();
    public void newTraining();
    public boolean isCurrentGame();
}
