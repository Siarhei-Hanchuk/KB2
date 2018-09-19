package by.siarhei.kb2.app.server;

import by.siarhei.kb2.app.server.entities.ArmyShop;
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
    public static final int VIEW_MODE_WEEK_FINISHED = 4;
    public static final int VIEW_MODE_ARMY_SHOP = 5;
    public static final int VIEW_MODE_MENU2 = 6;
    public static final int VIEW_MODE_STATUS = 7;
    public static final int VIEW_MODE_PLAYER_ARMY = 8;
    public static final int VIEW_MODE_MAP = 9;

    public static final int GAME_MENU_MAIN = -1;
    public static final int GAME_MENU_OTHER = 0;
    public static final int GAME_MENU_MAGIC = 2;
    public static final int GAME_MENU_STATUS = 3;
    public static final int GAME_MENU_MAP = 4;

    private int viewMode = VIEW_MODE_GRID;
    private int gameMenuMode = GAME_MENU_MAIN;

    private transient Message message;
    private transient Menu menu;
    private transient Game game;
    private ArmyShop currentArmyShop;

    public GameDispatcher(Game game) {
        this.game = game;
    }

    public void request(Request data) {
        switch (data.getAction()) {
            case Request.ACTION_MOVE:
                if(game.getDays() == 0) {
                    game.weekUpdate();
                    setViewMode(VIEW_MODE_WEEK_FINISHED);
                    break;
                }
                MapPoint mp = game.move(data.getX(), data.getY());
                if(mp.getEntity() != null) {
                    actionWithObject(mp);
                } else {
                    game.weekUpdate();
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

            case Request.ACTION_BUY_ARMY:
                game.buyArmy(currentArmyShop, data.getMenuItem());
                break;
            case Request.ACTION_EXIT:
                setViewMode(VIEW_MODE_GRID);
                break;
            case Request.ACTION_OPEN_GAME_MENU:
                gameMenuClick(data.getMenuItem());
                break;
        }
    }

    private void gameMenuClick(int item) {
        switch (gameMenuMode) {
            case GAME_MENU_MAIN:
                if(item == GAME_MENU_MAP || item == GAME_MENU_OTHER || item == GAME_MENU_MAGIC)
                    gameMenuMode = item;
                if(item == GAME_MENU_STATUS)
                    setViewMode(VIEW_MODE_STATUS);
                break;
            case GAME_MENU_MAP:
                switch (item) {
                    case 0:
                        setViewMode(VIEW_MODE_MAP);
                        break;
                    case 1:
                        //
                        break;
                    case 2:
                        gameMenuMode = GAME_MENU_MAIN;
                }
                break;
            case GAME_MENU_OTHER:
                switch (item) {
                    case 0:
                        setViewMode(VIEW_MODE_PLAYER_ARMY);
                        break;
                    case 1:
                        //
                        break;
                    case 2:
                        //
                        break;
                    case 3:
                        //
                        break;
                    case 4:
                        gameMenuMode = GAME_MENU_MAIN;
                }
                break;
            case GAME_MENU_MAGIC:
                switch (item) {
                    case 0:
                        //
                        break;
                    case 1:
                        //
                        break;
                    case 2:
                        //
                        break;
                    case 3:
                        //
                        break;
                    case 4:
                        gameMenuMode = GAME_MENU_MAIN;
                }
                break;
        }
    }

    private void actionWithObject(MapPoint mp) {
        if(mp.getEntity() instanceof GoldenChest) {
            System.out.println("M3");
            viewMode = VIEW_MODE_MENU2;
            this.menu = Server.getMenuFactory().getMenu(mp);
            return;
        }
        if(mp.getEntity() instanceof City) {
            viewMode = VIEW_MODE_MENU;
            this.menu = Server.getMenuFactory().getMenu(mp);
            return;
        }
        if(mp.getEntity() instanceof GuidePost) {
            viewMode = VIEW_MODE_MESSAGE;
            this.message = Server.getMessageFactory().getMessage(mp.getEntity());
            return;
        }
        if(mp.getEntity() instanceof ArmyShop) {
            viewMode = VIEW_MODE_ARMY_SHOP;
            this.currentArmyShop = (ArmyShop) mp.getEntity();
            return;
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

    public ArmyShop getCurrentArmyShop() {
        return currentArmyShop;
    }

    public int getGameMenuMode() {
        return gameMenuMode;
    }
}
