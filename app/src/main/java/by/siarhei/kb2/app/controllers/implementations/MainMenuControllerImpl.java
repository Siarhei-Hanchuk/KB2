package by.siarhei.kb2.app.controllers.implementations;

import by.siarhei.kb2.app.controllers.ApplicationController;
import by.siarhei.kb2.app.controllers.MainMenuController;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.Server;

public class MainMenuControllerImpl extends ApplicationController implements MainMenuController {
    private Server server = null;

    public MainMenuControllerImpl() {
        setContentView(getViewFactory().getMainMenuView(this));
    }

    @Override
    public boolean isCurrentGame() {
        return getGame() != null;
    }

    @Override
    public void newGame() {
//        TODO - check
        createGame(Game.MODE_GAME);
    }

    @Override
    public void newTraining() {
        //        TODO - check
//        setGame(createGame(Game.MODE_TEST));
        createGame(Game.MODE_TRAINING);
    }

    @Override
    public boolean loadGame() {
        Game game = getStorage().loadGame("save1");
        if(game != null) {
            MainViewControllerImpl controller = new MainViewControllerImpl();
            game.onWeekUpdate(controller);
            game.onEntityActivate(controller);
//            TODO
//            setGame(game);
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
        // TODO - check
//        setGame(createGame(Game.MODE_TEST));
        createGame(Game.MODE_TEST);
    }

    private void createGame(int mode) {
        MainViewControllerImpl controller = new MainViewControllerImpl();
//        server = Server.create(mode);
    }

    // TODO - remove
    @Override
    public Game getGame() {
        return null;
    }
}
