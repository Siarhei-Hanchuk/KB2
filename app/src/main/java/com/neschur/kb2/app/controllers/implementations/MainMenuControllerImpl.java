package com.neschur.kb2.app.controllers.implementations;

import com.neschur.kb2.app.controllers.ApplicationController;
import com.neschur.kb2.app.controllers.MainMenuController;
import com.neschur.kb2.app.models.Game;
import com.neschur.kb2.app.views.ViewFactory;

public class MainMenuControllerImpl extends ApplicationController implements MainMenuController {
    public MainMenuControllerImpl() {
        setContentView(ViewFactory.getMainMenuView(this));
    }

    @Override
    public boolean isCurrentGame() {
        return getGame() != null;
    }

    @Override
    public void newGame() {
        setGame(new Game(new MainViewControllerImpl(), 1));

    }

    @Override
    public void newTraining() {
        setGame(new Game(new MainViewControllerImpl(), 0));
    }

    @Override
    public void loadGame() {
        Game game = getStorage().loadGame("save1");
        setGame(game);
        new MainViewControllerImpl();
    }

    @Override
    public void saveGame() {
        getStorage().saveGame(getGame(), "save1");
    }

    @Override
    public void viewClose() {
        setContentView(ViewFactory.getMainView(new MainViewControllerImpl()));
    }
}
