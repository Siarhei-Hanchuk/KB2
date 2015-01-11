package com.neschur.kb2.app.controllers;

import android.graphics.Canvas;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.ui.CityMenu;
import com.neschur.kb2.app.ui.Menu;
import com.neschur.kb2.app.views.MainView;
import com.neschur.kb2.app.views.Drawable;
import com.neschur.kb2.app.views.MenuView;

/**
 * Created by siarhei on 6.6.14.
 */
public class MainController implements Drawable {
    private MainActivity activity;
    private UIController uiController;
    private GameController gameController;
    private MenuController menuController;
    private MainView mainView;

    public MainController(MainActivity activity) {
        this.activity = activity;
    }

    public void start(){
        menuController = new MenuController(activity, this);
        gameController = new GameController(this);
        uiController = new UIController(activity, this);
        mainView = new MainView(activity, this);
        activity.setContentView(mainView);
    }

    public void draw(Canvas canvas) {
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

    public void activateEntity(Entity entity) {
        menuController.updateMenu(new CityMenu((City)entity, gameController));
        MenuView view = menuController.getView();
        android.widget.FrameLayout.LayoutParams params = new android.widget.FrameLayout.LayoutParams(400, 400);
        view.setLayoutParams(params);
        activity.setContentView(view);
    }

    public void closeMenu() {
        activity.setContentView(mainView);
    }
}
