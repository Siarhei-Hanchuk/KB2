package by.siarhei.kb2.app.server;

import by.siarhei.kb2.app.DebugLogger;
import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.builders.GameBuilder;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.ui.menus.MenuFactory;
import by.siarhei.kb2.app.ui.messages.MessageFactory;

public class Server {
    private static Server server = null;
    private static I18n i18n;

    private Game game;
    private GameDispatcher gameDispatcher;
    private Response mResponse;
    private boolean viewCached = false;

    private Server(Game game) {
        this.game = game;
        this.gameDispatcher = new GameDispatcher(game);
        this.mResponse = new Response(game, gameDispatcher);
    }

    public boolean request(Request data) {
        DebugLogger.logRequest(data);
        viewCached = false;

        gameDispatcher.request(data);
        return true;
    }

    public static Server getServer() {
//        if(server == null) {
//            Game game = GameBuilder.build(Game.MODE_TEST);
//            server = new Server(game);
//        }

        return server;
    }

    public Response getView() {
        if(!viewCached) {
            viewCached = true;
            mResponse.refresh();
            DebugLogger.logView(mResponse);
        }
        return mResponse;
    }

    public static void create(int mode) {
        Game game = GameBuilder.build(mode);
        server = new Server(game);
    }

    public static MessageFactory getMessageFactory() {
        return new MessageFactory(getServer().game, i18n);
    }

    public static MenuFactory getMenuFactory() {
        return new MenuFactory(getServer().game, i18n);
    }

    public static void setI18n(I18n i18n) {
        Server.i18n = i18n;
    }

    public static I18n getI18n() {
        return i18n;
    }
}
