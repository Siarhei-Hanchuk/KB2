package by.siarhei.kb2.app.models;

import com.neschur.kb2.app.R;
import by.siarhei.kb2.app.controllers.GameCallback;
import by.siarhei.kb2.app.countries.Country;
import by.siarhei.kb2.app.countries.World;
import by.siarhei.kb2.app.entities.ArmyShop;
import by.siarhei.kb2.app.entities.Captain;
import by.siarhei.kb2.app.entities.Castle;
import by.siarhei.kb2.app.entities.City;
import by.siarhei.kb2.app.entities.Entity;
import by.siarhei.kb2.app.entities.Magician;
import by.siarhei.kb2.app.entities.Metro;
import by.siarhei.kb2.app.entities.Moving;
import by.siarhei.kb2.app.entities.Nave;
import by.siarhei.kb2.app.entities.Sorcerer;
import by.siarhei.kb2.app.warriors.Warrior;
import by.siarhei.kb2.app.warriors.WarriorFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Game implements Serializable {
    public static final int MODE_TRAINING = 0;
    public static final int MODE_GAME = 1;
    public static final int MODE_TEST = 2;

    transient private GameCallback callbacks;

    private final World world;
    private final Player player;
    private Nave nave;
    private int weeks = 999;
    private int days = 200;
    private int currentWorker = -1;
    private final int mode;
    private final TrainingData trainingData = new TrainingData();

    public Game(GameCallback callbacks, int mode) {
        this.mode = mode;
        this.callbacks = callbacks;
        world = new World(mode);
        player = new Player(world.getCountry(0), mode);
        if (mode == MODE_GAME) {
            weeks = 200 - 1;
        } else if (mode == MODE_TRAINING) {
            weeks = 600 - 1;
        }
    }

    public int getMode() {
        return mode;
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public void moveEntities() {
        HashMap<Entity, Boolean> movedEntity = new HashMap<>();
        Glade glade = player.getCountry();
        for (int x = player.getX() - 2; x <= player.getX() + 2; x++) {
            for (int y = player.getY() - 2; y <= player.getY() + 2; y++) {
                Entity entity = glade.getMapPoint(x, y).getEntity();
                if (entity != null) {
                    if(movedEntity.get(entity) != null) {
                        continue;
                    }
                    movedEntity.put(entity, true);
                    if ((entity instanceof Captain && player.isCaptainsActivated())
                            || entity instanceof Sorcerer) {
                        if(player.distanceToEntity(entity) <= 1 &&
                                ((Moving) entity).canMoveTo(player.getMapPoint())) {
                            actionWithObject(glade.getMapPoint(x, y));
                        } else {
                            ((Moving) entity).moveInDirection(player.getMapPoint());
                        }
                    } else if (entity instanceof Magician) {
                        ((Moving) entity).moveInRandomDirection();
                    }
                }
            }
        }
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
        for (int i = 0; citiesIterator.hasNext(); i++) {
            city = citiesIterator.next();
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

        MapPoint mp = player.getCountry().getMapPoint(x + dx, y + dy);

        if (mp.getEntity() == null) {
            if (currentWorker > -1) {
                if (currentWorker == 0 && mp.getLand() == R.drawable.water) {
                    mp.setLand(R.drawable.plot);
                    currentWorker = -1;
                } else
                if (currentWorker == 1 && mp.getLand() == R.drawable.forest) {
                    mp.setLand(R.drawable.land);
                    currentWorker = -1;
                } else
                if (currentWorker == 2 && mp.getLand() == R.drawable.land) {
                    mp.setLand(R.drawable.water);
                    currentWorker = -1;
                } else
                if (currentWorker == 3 && mp.getLand() == R.drawable.stone) {
                    mp.setLand(R.drawable.land);
                    currentWorker = -1;
                } else {
                    player.changeWorker(currentWorker, +1);
                    currentWorker = -1;
                }
            } else if (player.inNave()) {
                if (mp.getLand() == R.drawable.land || mp.getLand() == R.drawable.sand) {
                    player.setNave(null);
                    player.move(x + dx, y + dy);
                }
                if (mp.getLand() == R.drawable.water || mp.getLand() == R.drawable.plot) {
                    player.move(x + dx, y + dy);
                }
            } else {
                if (mp.getLand() == R.drawable.land || mp.getLand() == R.drawable.plot
                        || mp.getLand() == R.drawable.sand) {
                    player.move(x + dx, y + dy);
                }
            }
        } else {
            actionWithObject(mp);
            return true;
        }

        tryActivateCaptains();
        moveEntities();
        weekUpdate();
        return true;
    }

    private void tryActivateCaptains() {
        if(!player.isCaptainsActivated() && player.hasArmy()) {
            player.activateCaptains();
        }
    }

    private void actionWithObject(MapPoint mp) {
        if (mp.getEntity() instanceof Nave) {
            player.setNave((Nave) mp.getEntity());
            player.move(mp);
        } else if (mp.getEntity() instanceof Metro) {
            player.move(player.getCountry().getLinkedMetroPoint((Metro) mp.getEntity()));
        } else if (mp.getEntity() instanceof Castle) {
            if (player.getY() > mp.getY())
                callbacks.activateEntity(mp.getEntity());
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

    public void changeCountry(int n) {
        Country country = getWorld().getCountry(n);
        country.createMaps();
        player.changeCountry(country);
    }

    public TrainingData getTrainingData() {
        return trainingData;
    }

    public void setCallbacks(GameCallback callbacks) {
        this.callbacks = callbacks;
    }

}
