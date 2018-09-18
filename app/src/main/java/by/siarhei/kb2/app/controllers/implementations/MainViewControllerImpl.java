package by.siarhei.kb2.app.controllers.implementations;

import by.siarhei.kb2.app.controllers.ApplicationController;
import by.siarhei.kb2.app.controllers.listeners.ActivationEntityListener;
import by.siarhei.kb2.app.controllers.listeners.WeekFinishListener;
import by.siarhei.kb2.app.server.GameGrid;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;
import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.Game;
import by.siarhei.kb2.app.server.models.TrainingData;

public class MainViewControllerImpl {

    public MainViewControllerImpl() {

    }

    public void touchDown() {
        playerMove(0, +1);
    }

    public void touchUp() {
        playerMove(0, -1);
    }

    public void touchRight() {
        playerMove(+1, 0);
    }

    public void touchLeft() {
        playerMove(-1, 0);
    }

    public void touchUpRight() {
        playerMove(+1, -1);
    }

    public void touchUpLeft() {
        playerMove(-1, -1);
    }

    public void touchDownRight() {
        playerMove(+1, +1);
    }

    public void touchDownLeft() {
        playerMove(-1, +1);
    }

    public void touchMenu(int i) {
        GameGrid grid = null;//getGameGrid();
        switch (grid.getMode()) {
            case 0:
                switch (i) {
                    case 0:
                        grid.setMode(1);
//                        view.refresh();
                        break;
                    case 1:
//                        setContentView(getViewFactory().getWorkersMenuView(this));
                        break;
                    case 2:
                        grid.setMode(2);
//                        view.refresh();
                        break;
                    case 3:
                        new PlayerViewsControllerImpl("status");
                        grid.setMode(0);
                        break;
                    case 4:
                        grid.setMode(3);
//                        view.refresh();
                        break;
                }
                break;
            case 1:
                switch (i) {
                    case 0:
                        new PlayerViewsControllerImpl("army");
                        grid.setMode(0);
                        break;
                    case 1:
//                        getGame().finishWeek();
                        grid.setMode(0);
                        break;
                    case 4:
                        grid.setMode(0);
//                        view.refresh();
                        break;
                }
                break;
            case 2:
                switch (i) {
                    case 0:
                        new PlayerViewsControllerImpl("magic");
                        grid.setMode(0);
                        break;
                    case 4:
                        grid.setMode(0);
//                        view.refresh();
                        break;
                }
                break;
            case 3:
                switch (i) {
                    case 0:
                        new PlayerViewsControllerImpl("map");
                        break;
                    case 1:
//                        if (getGame().getPlayer().inNave()) {
////                            setContentView(getViewFactory().getCountryMenuView(this));
//                        }
                        break;
                    case 2:
                        grid.setMode(0);
//                        view.refresh();
                        break;
                }
                break;
        }
    }

    private void playerMove(int dx, int dy) {
        Request request = new Request();
        request.setAction(Request.ACTION_MOVE);
        request.setMoveTo(dx, dy);
        Server.getServer().request(request);
    }
}
