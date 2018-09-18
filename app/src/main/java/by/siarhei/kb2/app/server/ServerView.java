package by.siarhei.kb2.app.server;

import by.siarhei.kb2.app.ui.menus.Menu;
import by.siarhei.kb2.app.ui.messages.Message;

public class ServerView {
    private transient GameGrid gameGrid;
    private int viewMode;
    private transient Message message;
    private transient Menu menu;
    private int money;

    private transient Game game;
    private transient GameDispatcher gameDispatcher;

    public ServerView(Game game, GameDispatcher gameDispatcher) {
        this.gameDispatcher = gameDispatcher;
        this.game = game;
    }

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    public int getViewMode() {
        return viewMode;
    }

    public Message getMessage() {
        return message;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getMoney() {
        return money;
    }

    public void refresh() {
        gameGrid = new GameGrid(game);
        menu = gameDispatcher.getMenu();
        message = gameDispatcher.getMessage();
        viewMode = gameDispatcher.getViewMode();
        money = game.getPlayer().getMoney();
    }
}
