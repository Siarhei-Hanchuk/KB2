package com.neschur.kb2.app.controllers;

import android.app.Activity;

import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.models.GameGrid;
import com.neschur.kb2.app.views.ViewFactory;

public class MainViewControllerImpl extends ApplicationController implements MainViewController,
        ActivateCallback {
    private GameGrid gameGrid;

    public MainViewControllerImpl(Activity activity) {
        super(activity);
        setContentView(ViewFactory.getMainView(this));
    }

    @Override
    public GameGrid getGameGrid() {
        if (gameGrid == null)
            gameGrid = new GameGrid(getGameController());
        gameGrid.update();
        return gameGrid;
    }

    @Override
    public void touchDown() {
        getGameController().move(0, +1);
    }

    @Override
    public void touchUp() {
        getGameController().move(0, -1);
    }

    @Override
    public void touchRight() {
        getGameController().move(+1, 0);
    }

    @Override
    public void touchLeft() {
        getGameController().move(-1, 0);
    }

    @Override
    public void activateEntity(Entity entity) {

    }

    @Override
    public void activateBattle(Fighting fighting) {

    }

    @Override
    public void touchUpRight() {
        getGameController().move(+1, -1);
    }

    @Override
    public void touchUpLeft() {
        getGameController().move(-1, -1);
    }

    @Override
    public void touchDownRight() {
        getGameController().move(+1, +1);
    }

    @Override
    public void touchDownLeft() {
        getGameController().move(-1, +1);
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
                        setContentView((new ViewFactory(this)).getWorkersMenuView());
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
//                        setContentView(ViewFactory.getArmyView(this));
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
//                        setContentView(ViewFactory.getMagicView(this));
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
//                        setContentView((new ViewFactory(this)).getMapView());
                        break;
                    case 1:
                        if (getGameController().getPlayer().inNave()) {
                            setContentView((new ViewFactory(this)).getCountryMenuView());
                        }
                        break;
                    case 2:
                        grid.setMode(0);
                        break;
                }
                break;
        }
    }

    @Override
    public void viewClose() {

    }
}
