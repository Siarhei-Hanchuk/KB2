package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.GameGrid;
import com.neschur.kb2.app.ui.UiFactory;
import com.neschur.kb2.app.views.MainView;
import com.neschur.kb2.app.views.ViewClosable;

/**
 * Created by siarhei on 6.6.14.
 */
public class MainController implements ViewClosable {
    private MainActivity activity;
    private GameController gameController;
    private MainView mainView;

    public MainController(MainActivity activity) {
        this.activity = activity;
    }

    public void start() {
        gameController = new GameController(this);
        mainView = new MainView(activity, this);
        activity.setContentView(mainView);

        UiFactory.create(activity, this);
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
            case 3:
                activity.setContentView(UiFactory.getMapView());
                return;
            case 4:
                if (gameController.getPlayer().inNave()) {
                    activity.setContentView(UiFactory.getMenuView());
                }
                return;
        }
    }

    public void activateEntity(Entity entity) {
        activity.setContentView(UiFactory.getViewForEntity(entity));
    }

    private void resetView() {
        activity.setContentView(mainView);
    }

    public GameController getGameController() {
        return gameController;
    }

    @Override
    public void viewClose() {
        resetView();
    }
}
