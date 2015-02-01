package com.neschur.kb2.app.controllers;

import android.content.Context;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.Storage;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.models.GameGrid;
import com.neschur.kb2.app.models.battle.BattleFinishing;
import com.neschur.kb2.app.ui.UiFactory;
import com.neschur.kb2.app.views.BattleView;
import com.neschur.kb2.app.views.MainMenuView;
import com.neschur.kb2.app.views.MainView;
import com.neschur.kb2.app.views.View;
import com.neschur.kb2.app.views.interfaces.ViewController;

public class MainController implements BattleFinishing, ViewController {
    private MainActivity activity;
    private GameController gameController;
    private MainView mainView;
    private MainMenuView mainMenuView;
    private BattleView battleView;
    private int gameMode = 0;
    private GameGrid gameGrid;
    private UiFactory uiFactory;

    public MainController(MainActivity _activity) {
        activity = _activity;
        mainMenuView = new MainMenuView(activity, this);
        activity.setContentView(mainMenuView);

        uiFactory = new UiFactory(activity, this);
    }

    public void newGame() {
        gameController = new GameController(this, GameController.MODE_GAME);
        mainView = new MainView(this);
        activity.setContentView(mainView);
        gameMode = 0;
    }

    public void newTraining() {
        gameController = new GameController(this, GameController.MODE_TRAINING);
        mainView = new MainView(this);
        activity.setContentView(mainView);
        gameMode = 0;

        gameController.getPlayer().getMemory().showAll();
    }

    public boolean isCurrentGame() {
        return gameController != null;
    }

    public GameGrid getGameGrid() {
        if (gameGrid == null)
            gameGrid = new GameGrid(gameController);
        return gameGrid;
    }

    public void touchDown() {
        gameController.move(0, +1);
    }

    public void touchUp() {
        gameController.move(0, -1);
    }

    public void touchRight() {
        gameController.move(+1, 0);
    }

    public void touchLeft() {
        gameController.move(-1, 0);
    }

    public void touchUpRight() {
        gameController.move(+1, -1);
    }

    public void touchUpLeft() {
        gameController.move(-1, -1);
    }

    public void touchDownRight() {
        gameController.move(+1, +1);
    }

    public void touchDownLeft() {
        gameController.move(-1, +1);
    }

    public void touchMenu(int i) {
        GameGrid grid = getGameGrid();
        switch (grid.getMode()) {
            case 0:
                switch (i) {
                    case 0:
                        grid.setMode(1);
                        break;
                    case 1:
                        activity.setContentView(uiFactory.getWorkersMenuView());
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
                        activity.setContentView(uiFactory.getArmyView());
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
                        activity.setContentView(uiFactory.getMagicView());
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
                        activity.setContentView(uiFactory.getMapView());
                        break;
                    case 1:
                        if (gameController.getPlayer().inNave()) {
                            activity.setContentView(uiFactory.getCountryMenuView());
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
        View view = uiFactory.getViewForEntity(entity);
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
        BattleController battleController = new BattleController(this, gameController.getPlayer(), fighting);
        battleView = new BattleView(activity, battleController, this);
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
        mainView = new MainView(this);
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
}
