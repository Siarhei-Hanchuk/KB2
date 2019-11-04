package by.siarhei.kb2.app.server;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.entities.ArmyShop;
import by.siarhei.kb2.app.server.entities.Captain;
import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.entities.Fighting;
import by.siarhei.kb2.app.server.entities.GoldenChest;
import by.siarhei.kb2.app.server.entities.GuidePost;
import by.siarhei.kb2.app.server.entities.Nave;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.models.Magic;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Player;
import by.siarhei.kb2.app.server.models.battle.Battle;
import by.siarhei.kb2.app.server.models.battle.BattleField;
import by.siarhei.kb2.app.server.warriors.WarriorFactory;
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
    public static final int VIEW_MODE_MAGIC = 10;
    public static final int VIEW_MODE_BATTLE_QUESTION = 11;
    public static final int VIEW_MODE_BATTLE_RESULT = 12;
    public static final int VIEW_MODE_BATTLE = 13;

    public static final int GAME_MENU_MAIN = -1;
    public static final int GAME_MENU_OTHER = 0;
    public static final int GAME_MENU_WORKERS = 1;
    public static final int GAME_MENU_MAGIC = 2;
    public static final int GAME_MENU_STATUS = 3;
    public static final int GAME_MENU_MAP = 4;

    private int viewMode = VIEW_MODE_GRID;
    private int gameMenuMode = GAME_MENU_MAIN;

    private transient Message message;
    private transient Menu menu;
    private transient Game game;
    private ArmyShop currentArmyShop;
    private Fighting currentFighting;
    private boolean lock = false;

    public GameDispatcher(Game game) {
        this.game = game;
        this.lock = false;
    }

    public void request(Request data) {
        switch (data.getAction()) {
            case Request.ACTION_MOVE:
                if(game.getDays() == 0) {
                    game.weekUpdate();
                    viewMode = VIEW_MODE_WEEK_FINISHED;
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
                viewMode = VIEW_MODE_GRID;
                break;
            case Request.ACTION_SELECT:
                Menu menu = getMenu();
                if(menu.select(data.getMenuItem()))
                    viewMode = VIEW_MODE_GRID;
                break;
            case Request.ACTION_BUY_ARMY:
                game.buyArmy(currentArmyShop, data.getMenuItem());
                break;
            case Request.ACTION_EXIT:
                viewMode = VIEW_MODE_GRID;
                break;
            case Request.ACTION_OPEN_GAME_MENU:
                gameMenuClick(data.getMenuItem());
                break;
            case Request.ACTION_GIVE_ARMY:
                // TODO - move to model
                game.getPlayer().pushArmy(WarriorFactory.create(data.getData()), 1);
                game.getPlayer().getMagic().downMagic(Magic.GIVE_ARMY_MAGIC);
                viewMode = VIEW_MODE_GRID;
                break;
            case Request.ACTION_ACCEPT_BATTLE:
                startBattle(currentFighting);
                break;
            case Request.ACTION_DECLINE_BATTLE:
                viewMode = VIEW_MODE_GRID;
                break;
            case Request.ACTION_BATTLE_MOVE:
//                BattleField battleField = game.getBattleField();
                Battle battle = game.getBattle();


                battle.interact(data.getX(), data.getY());

                break;
        }
    }

    private void gameMenuClick(int item) {
        switch (gameMenuMode) {
            case GAME_MENU_MAIN:
                if(item == GAME_MENU_MAP || item == GAME_MENU_OTHER || item == GAME_MENU_MAGIC)
                    gameMenuMode = item;
                if(item == GAME_MENU_STATUS)
                    viewMode = VIEW_MODE_STATUS;
                if(item == GAME_MENU_WORKERS) {
                    menu = Server.getMenuFactory().getWorkersMenu();
                    viewMode = VIEW_MODE_MENU;
                }
                break;
            case GAME_MENU_MAP:
                switch (item) {
                    case 0:
                        viewMode = VIEW_MODE_MAP;
                        break;
                    case 1:

                        if(game.getPlayer().getMapPoint().getLand() == R.drawable.water) {
                            menu = Server.getMenuFactory().getCountryMenu();
                            viewMode = VIEW_MODE_MENU;
                        } else {
                            message = Server.getMessageFactory().getCountySelectorUnavailableMessage();
                            viewMode = VIEW_MODE_MESSAGE;
                        }
                        break;
                    case 2:
                        gameMenuMode = GAME_MENU_MAIN;
                }
                break;
            case GAME_MENU_OTHER:
                switch (item) {
                    case 0:
                        viewMode = VIEW_MODE_PLAYER_ARMY;
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
                        viewMode = VIEW_MODE_MAGIC;
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
        if(mp.getEntity() instanceof Captain) {
            viewMode = VIEW_MODE_BATTLE_QUESTION;
            this.currentFighting = (Fighting) mp.getEntity();
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

    public Fighting getCurrentFighting() {
        return currentFighting;
    }

    private void startBattle(Fighting fighting) {
        viewMode = VIEW_MODE_BATTLE;
        game.startBattle(fighting);
    }
}
