package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.World;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.entities.Nave;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.Player;

import java.io.Serializable;

public class GameController implements Serializable {
    public static final int MODE_GAME = 1;
    public static final int MODE_TRAINING = 2;

    transient private MainController mainController;

    private World world;
    private Player player;
    private Nave nave;
    private int weeks;
    private int days = 0;
    private int currentWorker = -1;


    public GameController(MainController mainController, int mode) {
        this.mainController = mainController;
        if (mode == MODE_GAME) {
            world = new World();
            player = new Player(world.getCountry(0), Player.MODE_GAME);
            weeks = 200;
        } else if (mode == MODE_TRAINING) {
            world = new World(true);
            player = new Player(world.getCountry(0), Player.MODE_TRAINING);
            weeks = 600;
        }

    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public void moveEntities() {
//        Sorcerer sorcerer = player.getCountry().getSorcerer();
//        sorcerer.moveTo(player.getX(), player.getY());
    }

    public void weekUpdate() {
        if (days > 0) {
            days--;
        } else {
            days = 200;
            weeks--;
        }
    }

    public void move(int dx, int dy) {
        int x = player.getX();
        int y = player.getY();
        if (x + dx < 2 || x + dx > 62 || y + dy < 2 || y + dy > 62) {
            return;
        }

        moveEntities();
        weekUpdate();

        MapPoint mp = player.getCountry().getMapPoint(x + dx, y + dy);

        if (mp.getEntity() == null) {
            if (currentWorker > -1 && mp.getEntity() == null) {
                if (currentWorker == 0 && mp.getLand() == R.drawable.water) {
                    mp.setLand(R.drawable.plot);
                }
                if (currentWorker == 1 && mp.getLand() == R.drawable.forest) {
                    mp.setLand(R.drawable.land);
                }
                if (currentWorker == 2 && mp.getLand() == R.drawable.land) {
                    mp.setLand(R.drawable.water);
                }
                if (currentWorker == 3 && mp.getLand() == R.drawable.stone) {
                    mp.setLand(R.drawable.land);
                }
                currentWorker = -1;
            } else if (player.inNave()) {
                if (mp.getLand() == R.drawable.land || mp.getLand() == R.drawable.sand) {
                    player.setNave(null);
                    player.move(x + dx, y + dy);
                }
                if (mp.getLand() == R.drawable.water) {
                    player.move(x + dx, y + dy);
                }
            } else {
                if (mp.getLand() == R.drawable.land || mp.getLand() == R.drawable.plot
                        || mp.getLand() == R.drawable.sand) {
                    player.move(x + dx, y + dy);
                }
            }
        } else {
            actionWithObject(player, mp);
        }
    }

    private void actionWithObject(Player player, MapPoint mp) {
        if (mp.getEntity() instanceof Nave) {
            player.setNave((Nave) mp.getEntity());
            player.move(mp.getX(), mp.getY());
        } else {
            mainController.activateEntity(mp.getEntity());
        }
    }

    public boolean getNave() {
        return nave != null;
    }

    public void createNave(int x, int y) {
        nave = new Nave(world.getCountry(0), x, y);
    }

    public void destroyNave() {
        nave.destroy();
        nave = null;
    }

    public void buyArmy(ArmyShop armyShop, int count) {
        if (armyShop.getCount() >= count &&
                player.armyAfford(armyShop.getWarrior()) >= count &&
                player.getMoney() >= armyShop.getWarrior().getPriceInShop() * count &&
                player.getWarriorSquadsCount() < Player.MAX_ARMY) {
            player.changeMoney(-armyShop.getWarrior().getPriceInShop() * count);
            armyShop.pullArmy(count);
            player.pushArmy(armyShop.getWarrior(), count);
        }
    }

    public void activateBattle(Fighting fighting) {
        mainController.activateBattle(fighting);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void selectWorker(int n) {
        if (player.getWorker(n) > 0) {
            currentWorker = n;
            player.changeWorker(n, -1);
        }
    }
}
