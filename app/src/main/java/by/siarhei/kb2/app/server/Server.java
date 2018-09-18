package by.siarhei.kb2.app.server;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.builders.GameBuilder;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.ui.menus.Menu;
import by.siarhei.kb2.app.ui.menus.MenuFactory3;
import by.siarhei.kb2.app.ui.messages.MessageFactory3;

public class Server {
    private static Server server = null;
    private Game game;
    private static I18n i18n;

    private Server(Game game) {
        this.game = game;
    }

    public boolean request(Request data) {
        GameDispatcher gameDispatcher = new GameDispatcher();
        gameDispatcher.request(data);

        return true;
    }

    public static Server getServer() {
        if(server == null) {
            Game game = GameBuilder.build(Game.MODE_TEST);
            server = new Server(game);
        }

        return server;
    }

    public static ServerView getView() {
        ServerView view = new ServerView();
        view.setGameGrid(new GameGrid(getServer().game));
        System.out.print("VM");
        System.out.println(getServer().game.getViewMode());
        view.setViewMode(getServer().game.getViewMode());
        view.setMessage(getServer().game.getMessage());
        view.setMenu(getServer().game.getMenu());
        return view;
    }

    public static void create(int mode) {
        Game game = GameBuilder.build(mode);
        server = new Server(game);
    }

    public static MessageFactory3 getMessageFactory() {
        System.out.println("Get message");
        return new MessageFactory3(getServer().game, i18n);
    }

    public static MenuFactory3 getMenuFactory() {
        System.out.println("Get menu");
        return new MenuFactory3(getServer().game, i18n);
    }

    public static void setI18n(I18n i18n) {
        Server.i18n = i18n;
    }
}
