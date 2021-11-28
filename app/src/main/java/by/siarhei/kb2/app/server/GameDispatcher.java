package by.siarhei.kb2.app.server;

import by.siarhei.kb2.app.BuildConfig;
import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.entities.ArmyShop;
import by.siarhei.kb2.app.server.entities.Captain;
import by.siarhei.kb2.app.server.entities.Castle;
import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.entities.Fighting;
import by.siarhei.kb2.app.server.entities.GoldenChest;
import by.siarhei.kb2.app.server.entities.GuidePost;
import by.siarhei.kb2.app.server.entities.Magician;
import by.siarhei.kb2.app.server.entities.MapNext;
import by.siarhei.kb2.app.server.entities.Metro;
import by.siarhei.kb2.app.server.entities.Nave;
import by.siarhei.kb2.app.server.entities.Sorcerer;
import by.siarhei.kb2.app.server.entities.Spell;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.models.Magic;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Player;
import by.siarhei.kb2.app.server.models.battle.Battle;
import by.siarhei.kb2.app.server.models.battle.BattleResult;
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
    private static final int GAME_MENU_WORKERS = 1;
    public static final int GAME_MENU_MAGIC = 2;
    private static final int GAME_MENU_STATUS = 3;
    public static final int GAME_MENU_MAP = 4;
    public static final int GAME_MENU_CHEATS = 5;
    public static final int GAME_MENU_CHEATS_1 = 6;
    public static final int GAME_MENU_CHEATS_2 = 7;
    public static final int GAME_MENU_CHEATS_3 = 8;

    private int viewMode = VIEW_MODE_GRID;
    private int gameMenuMode = GAME_MENU_MAIN;

    private transient Message message;
    private transient Menu menu;
    private final transient Game game;
    private ArmyShop currentArmyShop;
    private MapPoint currentFightingPoint;

    public GameDispatcher(Game game) {
        this.game = game;
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
                startBattle();
                break;
            case Request.ACTION_DECLINE_BATTLE:
                viewMode = VIEW_MODE_GRID;
                break;
            case Request.ACTION_BATTLE_MOVE:
                Battle battle = game.getBattle();
                if(battle.finished()) {
                    battleResult();
                } else {
                    battle.action(data.getX(), data.getY());
                }
                break;
            case Request.ACTION_MESSAGE_OK:
                Message message = getMessage();
                message.action();
                viewMode = VIEW_MODE_GRID;
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
                        break;
                    case 3:
                        if (BuildConfig.DEBUG)
                            gameMenuMode = GAME_MENU_CHEATS;
                        break;
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
                        break;
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
                        break;
                }
                break;
            case GAME_MENU_CHEATS:
                switch (item) {
                    case 0:
                        gameMenuMode = GAME_MENU_CHEATS_1;
                        break;
                    case 1:
                        gameMenuMode = GAME_MENU_CHEATS_2;
                        break;
//                    case 2:
//                        gameMenuMode = GAME_MENU_CHEATS_3;
//                        break;
//                    case 3:
//                        gameMenuMode = GAME_MENU_CHEATS_4;
//                        break;
                    case 4:
                        gameMenuMode = GAME_MENU_MAIN;
                        break;
                }
            case GAME_MENU_CHEATS_1:
                switch (item) {
                    case 0:
                        game.getPlayer().changeMoney(10000);
                        break;
                    case 1:
                        game.getPlayer().changeAuthority(1000);
                        break;
                    case 2:
                        game.getPlayer().upAvailableCountry();
                        game.getPlayer().upAvailableCountry();
                        game.getPlayer().upAvailableCountry();
                        game.getPlayer().upAvailableCountry();
                        break;
                    case 4:
                        gameMenuMode = GAME_MENU_MAIN;
                        break;
                }
            case GAME_MENU_CHEATS_2:
                switch (item) {
                    case 0:
//                        game.getWorld().getCountry(0).
                        break;
                    case 4:
                        gameMenuMode = GAME_MENU_MAIN;
                        break;
                }
        }
    }

    private void actionWithObject(MapPoint mp) {
        if(mp.getEntity() instanceof GoldenChest && !((GoldenChest) mp.getEntity()).isBonus()) {
            viewMode = VIEW_MODE_MENU2;
            this.menu = Server.getMenuFactory().getMenu(mp);
            return;
        }
        if(mp.getEntity() instanceof GoldenChest && ((GoldenChest) mp.getEntity()).isBonus()) {
            viewMode = VIEW_MODE_MESSAGE;
            this.message = Server.getMessageFactory().getMessage(mp);
            return;
        }
        if(mp.getEntity() instanceof City) {
            viewMode = VIEW_MODE_MENU;
            this.menu = Server.getMenuFactory().getMenu(mp);
            return;
        }
        if(mp.getEntity() instanceof GuidePost) {
            viewMode = VIEW_MODE_MESSAGE;
            this.message = Server.getMessageFactory().getMessage(mp);
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
        if(mp.getEntity() instanceof Captain || (mp.getEntity() instanceof Castle && !((Castle) mp.getEntity()).getStricken())) {
            viewMode = VIEW_MODE_BATTLE_QUESTION;
            this.currentFightingPoint = mp;
            return;
        }
        if (mp.getEntity() instanceof Metro) {
            Player player = game.getPlayer();
            Metro metro = (Metro) mp.getEntity();
            MapPoint targetPoint = player.getCountry().getLinkedMetroPoint(metro);
            if (targetPoint != null) {
                player.move(targetPoint);
            }
        }
        if (mp.getEntity() instanceof MapNext) {
            viewMode = VIEW_MODE_MESSAGE;
            this.message = Server.getMessageFactory().getMessage(mp);
            return;
        }
        if (mp.getEntity() instanceof Spell) {
            viewMode = VIEW_MODE_MESSAGE;
            this.message = Server.getMessageFactory().getMessage(mp);
            return;
        }
        if (mp.getEntity() instanceof Sorcerer) {
            viewMode = VIEW_MODE_MESSAGE;
            this.message = Server.getMessageFactory().getMessage(mp);
            return;
        }
        if (mp.getEntity() instanceof Magician) {
            viewMode = VIEW_MODE_MENU;
            this.menu = Server.getMenuFactory().getMenu(mp);
            return;
        }
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
        if(currentFightingPoint == null || currentFightingPoint.getEntity() == null) {
            return null;
        }

        return (Fighting) currentFightingPoint.getEntity();
    }

    private void startBattle() {
        viewMode = VIEW_MODE_BATTLE;
        game.startBattle(currentFightingPoint);
    }

    private void battleResult() {
        viewMode = VIEW_MODE_MESSAGE;
        BattleResult battleResult = game.finishBattle();
        this.message = battleResult.getMessage();
    }
}
