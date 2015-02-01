package com.neschur.kb2.app.controllers;

import android.content.Context;
import android.view.SurfaceView;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.Storage;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.models.GameGrid;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.models.battle.BattleFinishing;
import com.neschur.kb2.app.views.ViewFactory;

public class MainController implements BattleFinishing, MainViewController, PlayerViewsController,
        ArmyShopViewController  {
    private MainActivity activity;
    private GameController gameController;
    private SurfaceView mainView;
    private SurfaceView mainMenuView;
    private SurfaceView battleView;
    private int gameMode = 0;
    private GameGrid gameGrid;
    private ViewFactory viewFactory;

    public MainController(MainActivity _activity) {
        activity = _activity;
        viewFactory = new ViewFactory(this);

        mainMenuView = viewFactory.getMainMenuView();
        activity.setContentView(mainMenuView);
    }

    public void newGame() {
        gameController = new GameController(this, GameController.MODE_GAME);
        mainView = viewFactory.getMainView();
        activity.setContentView(mainView);
        gameMode = 0;
    }

    public void newTraining() {
        gameController = new GameController(this, GameController.MODE_TRAINING);
        mainView = viewFactory.getMainView();
        activity.setContentView(mainView);
        gameMode = 0;

        gameController.getPlayer().getMemory().showAll();
    }

    public boolean isCurrentGame() {
        return gameController != null;
    }

    @Override
    public GameGrid getGameGrid() {
        if (gameGrid == null)
            gameGrid = new GameGrid(gameController);
        gameGrid.update();
        return gameGrid;
    }

    @Override
    public void touchDown() {
        gameController.move(0, +1);
    }

    @Override
    public void touchUp() {
        gameController.move(0, -1);
    }

    @Override
    public void touchRight() {
        gameController.move(+1, 0);
    }

    @Override
    public void touchLeft() {
        gameController.move(-1, 0);
    }

    @Override
    public void touchUpRight() {
        gameController.move(+1, -1);
    }

    @Override
    public void touchUpLeft() {
        gameController.move(-1, -1);
    }

    @Override
    public void touchDownRight() {
        gameController.move(+1, +1);
    }

    @Override
    public void touchDownLeft() {
        gameController.move(-1, +1);
    }

    @Override
    public void touchMenu(int i) {
        GameGrid grid = getGameGrid();
        switch (grid.getMode()) {
            case 0:
                switch (i) {
                    case 0:
                        grid.setMode(1);
                        break;
                    case 1:
                        activity.setContentView(viewFactory.getWorkersMenuView());
                        break;
                    case 2:
                        grid.setMode(2);
                        break;
                    case 3:
                        break;
                    case 4:
                        grid.setMode(3);
                        break;
                }
                break;
            case 1:
                switch (i) {
                    case 0:
                        activity.setContentView(viewFactory.getArmyView());
                        grid.setMode(0);
                        break;
                    case 4:
                        grid.setMode(0);
                        break;
                }
                break;
            case 2:
                switch (i) {
                    case 0:
                        activity.setContentView(viewFactory.getMagicView());
                        grid.setMode(0);
                        break;
                    case 4:
                        grid.setMode(0);
                        break;
                }
                break;
            case 3:
                switch (i) {
                    case 0:
                        activity.setContentView(viewFactory.getMapView());
                        break;
                    case 1:
                        if (gameController.getPlayer().inNave()) {
                            activity.setContentView(viewFactory.getCountryMenuView());
                        }
                        break;
                    case 2:
                        grid.setMode(0);
                        break;
                }
                break;
        }
    }

    public void activateEntity(Entity entity) {
        SurfaceView view = viewFactory.getViewForEntity(entity);
        if (view != null)
            activity.setContentView(view);
    }

    private void resetView() {
        switch (gameMode) {
            case 0:
                activity.setContentView(mainView);
                break;
            case 1:
                activity.setContentView(battleView);
                break;
        }
    }

    public GameController getGameController() {
        return gameController;
    }

    @Override
    public void viewClose() {
        resetView();
    }

    public void activateBattle(Fighting fighting) {
        gameMode = 1;
        BattleControllerImpl battleController = new BattleControllerImpl(this, gameController.getPlayer(), fighting);
        battleView = viewFactory.getBattleView(battleController);
    }

    public void activateMainMenu() {
        activity.setContentView(mainMenuView);
    }

    public void exit() {
        activity.finish();
        System.exit(0);
    }

    public void saveGame() {
        Storage storage = new Storage(activity);
        storage.saveGame(gameController, "save1");
    }

    public void loadGame() {
        Storage storage = new Storage(activity);
        gameController = storage.loadGame("save1");
        gameController.setMainController(this);
        mainView = viewFactory.getMainView();
        activity.setContentView(mainView);
    }

    @Override
    public void battleFinish(boolean win) {
        gameMode = 0;
        resetView();
    }

    @Override
    public Context getContext() {
        return activity;
    }

    @Override
    public Player getPlayer() {
        return gameController.getPlayer();
    }

    @Override
    public void buyArmy(ArmyShop shop, int count) {
        gameController.buyArmy(shop, count);
    }
}
