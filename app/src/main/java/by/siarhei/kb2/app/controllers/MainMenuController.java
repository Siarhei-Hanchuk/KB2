package by.siarhei.kb2.app.controllers;

public interface MainMenuController extends ViewController {
    void exit();

    boolean saveGame();

    boolean loadGame();

    void newGame();

    void newTraining();

    boolean isCurrentGame();

    void newTestGame();
}
