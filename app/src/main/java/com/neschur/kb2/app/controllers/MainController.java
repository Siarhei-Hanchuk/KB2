package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.Storage;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.models.GameGrid;
import com.neschur.kb2.app.ui.UiFactory;
import com.neschur.kb2.app.views.BattleView;
import com.neschur.kb2.app.views.MainMenuView;
import com.neschur.kb2.app.views.MainView;
import com.neschur.kb2.app.views.View;
import com.neschur.kb2.app.views.ViewClosable;

public class MainController implements ViewClosable {
    private MainActivity activity;
    private GameController gameController;
    private MainView mainView;
    private MainMenuView mainMenuView;
    private BattleView battleView;
    private int gameMode = 0;

    public MainController(MainActivity activity) {
        this.activity = activity;
    }

    public void start() {
        mainMenuView = new MainMenuView(activity, this);
        activity.setContentView(mainMenuView);

        UiFactory.create(activity, this);
    }

    public void newGame() {
        gameController = new GameController(this, GameController.MODE_GAME);
        mainView = new MainView(activity, this);
        activity.setContentView(mainView);
    }

    public void newTraining() {
        gameController = new GameController(this, GameController.MODE_TRAINING);
        mainView = new MainView(activity, this);
        activity.setContentView(mainView);
    }

    public boolean isCurrentGame() {
        return gameController != null;
    }

    public GameGrid getGameGrid() {
        GameGrid gameGrid = new GameGrid(gameController);
        gameGrid.update();
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
        switch (i) {
            case 0:
                activity.setContentView(UiFactory.getArmyView());
                break;
            case 3:
                activity.setContentView(UiFactory.getMapView());
                break;
            case 4:
                if (gameController.getPlayer().inNave()) {
                    activity.setContentView(UiFactory.getMenuView());
                }
                break;
        }
    }

    public void activateEntity(Entity entity) {
        View view = UiFactory.getViewForEntity(entity);
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
        BattleController battleController = new BattleController(gameController.getPlayer(), fighting);
        battleView = new BattleView(activity, gameController, battleController, this);
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
        mainView = new MainView(activity, this);
        activity.setContentView(mainView);
    }
}
