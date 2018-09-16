package by.siarhei.kb2.app.server;

import by.siarhei.kb2.app.server.builders.GameBuilder;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.models.MapPoint;

public class Server {
    private static Server server = null;
    private Game game;

    private Server(Game game) {
        this.game = game;
    }

    public void request(Request data) {
        MapPoint mp = game.getPlayer().getMapPoint();
        System.out.print("Move:");
        System.out.print(mp.getX() + data.getX());
        System.out.print(" ");
        System.out.println(mp.getY() + data.getY());

        game.getPlayer().move(mp.getX() + data.getX(), mp.getX() + data.getY());
    }

    public static Server getServer() {
        if(server == null) {
            Game game = GameBuilder.build(Game.MODE_TEST);
            server = new Server(game);
        }

        return server;
    }

    public static GameGrid getGameGrid() {
        return new GameGrid(getServer().game);
    }
}
