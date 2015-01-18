package com.neschur.kb2.app.controllers;

import android.graphics.Canvas;
import android.view.View;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.ui.MenuFactory;
import com.neschur.kb2.app.ui.MessageFactory;
import com.neschur.kb2.app.ui.menus.CountryMenu;
import com.neschur.kb2.app.ui.menus.Menu;
import com.neschur.kb2.app.ui.messages.Message;
import com.neschur.kb2.app.views.Drawable;
import com.neschur.kb2.app.views.MainView;
import com.neschur.kb2.app.views.MapView;
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

        MenuFactory.create(activity, gameController);
        MessageFactory.create(activity, gameController);
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
                View mapView = new MapView(activity, this);
                activity.setContentView(mapView);
                return;
            case 4:
                if (gameController.getPlayer().inNave()) {
                    menuController.updateMenu(new CountryMenu(activity, gameController));
                    View menuView = menuController.getView();
                    activity.setContentView(menuView );
                }
                return;
        }
    }

    public void activateEntity(Entity entity) {
        Menu menu = MenuFactory.getMenu(entity);
        Message message = MessageFactory.getMessage(entity);
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
