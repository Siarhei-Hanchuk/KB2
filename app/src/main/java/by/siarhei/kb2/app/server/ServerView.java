package by.siarhei.kb2.app.server;

import by.siarhei.kb2.app.ui.menus.Menu;
import by.siarhei.kb2.app.ui.messages.Message;

public class ServerView {
    private GameGrid gameGrid;
    private int viewMode;
    private Message message;
    private Menu menu;

    public ServerView() {
    }

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    public void setGameGrid(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    public int getViewMode() {
        return viewMode;
    }

    public void setViewMode(int viewMode) {
        this.viewMode = viewMode;
    }

    public Message getMessage() {
        return message;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
