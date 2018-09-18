package by.siarhei.kb2.app.server;

import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.entities.GoldenChest;
import by.siarhei.kb2.app.server.entities.GuidePost;
import by.siarhei.kb2.app.server.entities.Nave;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Player;
import by.siarhei.kb2.app.ui.menus.Menu;
import by.siarhei.kb2.app.ui.messages.Message;

public class GameDispatcher {
    public static final int VIEW_MODE_GRID = 1;
    public static final int VIEW_MODE_MESSAGE = 2;
    public static final int VIEW_MODE_MENU = 3;

    private int viewMode = VIEW_MODE_GRID;

    private transient Message message;
    private transient Menu menu;
    private transient Game game;

    public GameDispatcher(Game game) {
        this.game = game;
    }

    public void request(Request data) {
        switch (data.getAction()) {
            case Request.ACTION_MOVE:
                MapPoint mp = game.move(data.getX(), data.getY());
                System.out.println("M1");
                if(mp.getEntity() != null) {
                    System.out.println("M2");
                    actionWithObject(mp);
                }
                break;

            case Request.ACTION_OK:
                setViewMode(VIEW_MODE_GRID);
                break;

            case Request.ACTION_SELECT:
                Menu menu = getMenu();
                if(menu.select(data.getMenuItem()))
                    setViewMode(VIEW_MODE_GRID);
                break;

            case Request.ACTION_EXIT:
                setViewMode(VIEW_MODE_GRID);
                break;
        }
    }

    private void actionWithObject(MapPoint mp) {
//        if(mp.getEntity() instanceof GoldenChest) {
//            System.out.println("M3");
//            viewMode = VIEW_MODE_MENU;
//            this.menu = Server.getMenuFactory().getMenu(mp.getEntity());
//            return;
//        }

        if(mp.getEntity() instanceof City) {
            viewMode = VIEW_MODE_MENU;
            this.menu = Server.getMenuFactory().getMenu(mp);
            return;
        }
        if(mp.getEntity() instanceof GuidePost) {
            viewMode = VIEW_MODE_MESSAGE;
            this.message = Server.getMessageFactory().getMessage(mp.getEntity());
        }
        if (mp.getEntity() instanceof Nave) {
            Player player = game.getPlayer();
            player.setNave((Nave) mp.getEntity());
            player.move(mp);
            return;
        }
// TODO - check
//        if (mp.getEntity() instanceof Nave) {
//            player.setNave((Nave) mp.getEntity());
//            player.move(mp);
//        } else if (mp.getEntity() instanceof Metro) {
//            player.move(player.getCountry().getLinkedMetroPoint((Metro) mp.getEntity()));
//        } else if (mp.getEntity() instanceof Castle) {
//            if (player.getY() > mp.getY())
//                activationEntityListener.activateEntity(mp.getEntity());
//        } else {
//            activationEntityListener.activateEntity(mp.getEntity());
//        }
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
}
