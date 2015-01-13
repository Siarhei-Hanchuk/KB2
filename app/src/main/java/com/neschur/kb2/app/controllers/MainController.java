package com.neschur.kb2.app.controllers;

import android.graphics.Canvas;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.ui.CountryMenu;
import com.neschur.kb2.app.ui.Menu;
import com.neschur.kb2.app.views.Drawable;
import com.neschur.kb2.app.views.MainView;
import com.neschur.kb2.app.views.MenuView;
import com.neschur.kb2.app.views.MessageView;

/**
 * Created by siarhei on 6.6.14.
 */
public class MainController implements Drawable {
    private MainActivity activity;
    private UIController uiController;
    private GameController gameController;
    private MenuController menuController;
    private MessageController messageController;
    private MainView mainView;

    public MainController(MainActivity activity) {
        this.activity = activity;
    }

    public void start() {
        menuController = new MenuController(activity, this);
        messageController = new MessageController(activity, this);
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

    public void touchMenu(int i) {
        switch (i) {
            case 4:
                if (gameController.getPlayer().inNave()) {
                    menuController.updateMenu(new CountryMenu(activity, gameController));
                    MenuView view = menuController.getView();
                    activity.setContentView(view);
                }
        }
    }

    public void activateEntity(Entity entity) {
        Menu menu = entity.getMenu(activity, gameController);
        String message = entity.getMessage(activity);
        if (menu != null) {
            menuController.updateMenu(menu);
            MenuView view = menuController.getView();
            activity.setContentView(view);
        }
        if (message != null) {
            messageController.updateMessage(message);
            MessageView view = messageController.getView();
            activity.setContentView(view);
        }
    }

    public void closeMenu() {
        activity.setContentView(mainView);
    }

    public GameController getGameController() {
        return gameController;
    }
}
