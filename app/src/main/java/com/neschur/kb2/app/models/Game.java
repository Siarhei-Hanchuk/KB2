package com.neschur.kb2.app.models;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameCallback;
import com.neschur.kb2.app.countries.World;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.entities.Nave;
import com.neschur.kb2.app.warriors.Warrior;
import com.neschur.kb2.app.warriors.WarriorFactory;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;

public class Game implements Serializable {
    public static final int MODE_TRAINING = 0;
    public static final int MODE_GAME = 1;
    public static final int MODE_TEST = 2;

    final transient private GameCallback callbacks;

    private final World world;
    private final Player player;
    private Nave nave;
    private int weeks = 999;
    private int days = 200;
    private int currentWorker = -1;

    public Game(GameCallback callbacks, int mode) {
        this.callbacks = callbacks;
        world = new World(mode);
        player = new Player(world.getCountry(0), mode);
        if (mode == MODE_GAME) {
            weeks = 200 - 1;
        } else if (mode == MODE_TRAINING) {
            weeks = 600 - 1;
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
//        sorcerer.moveInDirection(player.getMapPoint());
    }

    private void weekFinish() {
        Iterator<ArmyShop> shopsIterator = world.getArmyShops();
        Warrior war = WarriorFactory.createRandom();
        while (shopsIterator.hasNext()) {
            if (shopsIterator.next().getID() == war.getId())
                shopsIterator.next().resetCount();
        }

        City city = null;
        Iterator<City> citiesIterator = world.getCities();
        int n = (new Random()).nextInt(25);
        for (int i = 0; citiesIterator.hasNext(); city = citiesIterator.next(), i++) {
            if (i == n) {
                city.resetWorkers();
                break;
            }
        }
        callbacks.weekFinish(war.getTextId(), city);
    }

    public void weekUpdate() {
        if (days > 0) {
            days--;
        } else {
            days = 200;
            weeks--;
            weekFinish();
        }
    }

    public boolean move(int dx, int dy) {
        int x = player.getX();
        int y = player.getY();
        if (x + dx < 2 || x + dx > 62 || y + dy < 2 || y + dy > 62) {
            return false;
        }

        moveEntities();
        weekUpdate();

        MapPoint mp = player.getCountry().getMapPoint(x + dx, y + dy);

        if (mp.getEntity() == null) {
            if (currentWorker > -1) {
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
        return true;
    }

    private void actionWithObject(Player player, MapPoint mp) {
        if (mp.getEntity() instanceof Nave) {
            player.setNave((Nave) mp.getEntity());
            player.move(mp.getX(), mp.getY());
        } else {
            callbacks.activateEntity(mp.getEntity());
        }
    }

    public boolean getNave() {
        return nave != null;
    }

    public void createNave(int x, int y) {
        nave = new Nave(world.getCountry(0).getMapPoint(x, y));
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
        callbacks.activateBattle(fighting);
    }

    public void selectWorker(int n) {
        if (player.getWorker(n) > 0) {
            currentWorker = n;
            player.changeWorker(n, -1);
        }
    }

    public int getWeeks() {
        return weeks;
    }

    public void finishWeek() {
        weeks--;
        days = 200;
        weekFinish();
    }
}
