package by.siarhei.kb2.app.controllers.implementations;

import by.siarhei.kb2.app.controllers.ApplicationController;
import by.siarhei.kb2.app.controllers.listeners.ActivationEntityListener;
import by.siarhei.kb2.app.controllers.MainMenuController;
import by.siarhei.kb2.app.models.Game;

public class MainMenuControllerImpl extends ApplicationController implements MainMenuController {
    public MainMenuControllerImpl() {
        setContentView(getViewFactory().getMainMenuView(this));
    }

    @Override
    public boolean isCurrentGame() {
        return getGame() != null;
    }

    @Override
    public void newGame() {
        setGame(createGame(Game.MODE_TEST));
    }

    @Override
    public void newTraining() {
        setGame(createGame(Game.MODE_TEST));
    }

    @Override
    public boolean loadGame() {
        Game game = getStorage().loadGame("save1");
        if(game != null) {
            MainViewControllerImpl controller = new MainViewControllerImpl();
            game.onWeekUpdate(controller);
            game.onEntityActivate(controller);
            setGame(game);
            return true;
        }
        return false;
    }

    @Override
    public boolean saveGame() {
        return getStorage().saveGame(getGame(), "save1");
    }

    @Override
    public void viewClose() {
        new MainViewControllerImpl();
    }

    @Override
    public void newTestGame() {
        setGame(createGame(Game.MODE_TEST));
    }

    private Game createGame(int mode) {
        MainViewControllerImpl controller = new MainViewControllerImpl();
        Game game = new Game(mode);
        game.onWeekUpdate(controller);
        game.onEntityActivate(controller);
        return game;
    }
}
