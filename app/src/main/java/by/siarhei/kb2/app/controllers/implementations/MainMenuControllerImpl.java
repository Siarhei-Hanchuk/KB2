package by.siarhei.kb2.app.controllers.implementations;

import by.siarhei.kb2.app.controllers.ApplicationController;
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
        setGame(new Game(new MainViewControllerImpl(), Game.MODE_GAME));
    }

    @Override
    public void newTraining() {
        setGame(new Game(new MainViewControllerImpl(), Game.MODE_TRAINING));
    }

    @Override
    public void loadGame() {
        Game game = getStorage().loadGame("save1");
        if(game != null) {
            game.setCallbacks(new MainViewControllerImpl());
            setGame(game);
        }
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
        setGame(new Game(new MainViewControllerImpl(), Game.MODE_TEST));
    }
}
