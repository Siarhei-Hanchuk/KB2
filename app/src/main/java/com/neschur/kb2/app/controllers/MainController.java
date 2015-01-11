package com.neschur.kb2.app.controllers;

import android.graphics.Canvas;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.views.MainView;

/**
 * Created by siarhei on 6.6.14.
 */
public class MainController {
    private MainActivity activity;
    private UIController uiController;
    private GameController gameController;

    public MainController(MainActivity activity) {
        this.activity = activity;
    }

    public void start(){
        gameController = new GameController();
        uiController = new UIController(activity, this);
        MainView view = new MainView(activity, this);
        activity.setContentView(view);
    }

    public void rePaint(Canvas canvas) {
        uiController.paint(canvas);
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
}
