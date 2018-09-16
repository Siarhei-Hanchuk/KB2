package by.siarhei.kb2.app.server;

public class ServerView {
    private GameGrid gameGrid;
    private int viewMode;

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
}
