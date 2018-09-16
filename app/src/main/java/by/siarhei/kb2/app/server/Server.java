package by.siarhei.kb2.app.server;

import by.siarhei.kb2.app.server.builders.GameBuilder;
import by.siarhei.kb2.app.server.models.Game;

public class Server {
    private static Server server = null;
    private Game game;

    private Server(Game game) {
        this.game = game;
    }

    public boolean request(Request data) {
        game.move(data.getX(), data.getY());

        return true;
    }

    public static Server getServer() {
        if(server == null) {
            Game game = GameBuilder.build(Game.MODE_TEST);
            server = new Server(game);
        }

        return server;
    }

//    public static GameGrid getGameGrid() {
//        return new GameGrid(getServer().game);
//    }

    public static ServerView getView() {
        ServerView view = new ServerView ();
        view.setGameGrid(new GameGrid(getServer().game));
        view.setViewMode(getServer().game.getViewMode());
        return view;
    }

    public static void create(int mode) {
        Game game = GameBuilder.build(mode);
        server = new Server(game);
    }
}
