package by.siarhei.kb2.app.server;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.countries.Country;
import by.siarhei.kb2.app.server.countries.World;
import by.siarhei.kb2.app.server.entities.ArmyShop;
import by.siarhei.kb2.app.server.entities.Captain;
import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.entities.Magician;
import by.siarhei.kb2.app.server.entities.Moving;
import by.siarhei.kb2.app.server.entities.Nave;
import by.siarhei.kb2.app.server.entities.Sorcerer;
import by.siarhei.kb2.app.server.models.Glade;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Player;
import by.siarhei.kb2.app.server.warriors.Warrior;
import by.siarhei.kb2.app.server.warriors.WarriorFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Game implements Serializable {
    public static final int MODE_TRAINING = 0;
    public static final int MODE_GAME = 1;
    public static final int MODE_TEST = 2;

    public static final int WEEK_LENGTH = 200;

    final private World world;
    final private Player player;
    private int weeks;
    private int days = WEEK_LENGTH;
    private int currentWorker = -1;
    private City updatedCity;
    private Warrior updatedWarrior;

    public Game(World world, Player player, int weeks) {
        this.world = world;
        this.player = player;
        this.weeks = weeks;
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
                        // TODO - fix
//                        if(player.distanceToEntity(entity) <= 1 &&
//                                ((Moving) entity).canMoveTo(player.getMapPoint())) {
//                            actionWithObject(glade.getMapPoint(x, y));
//                        } else {
////                            ((Moving) entity).moveInDirection(player.getMapPoint());
//                            Mover mover = new Mover(glade);
//                            // TODO - check
//                            mover.moveInDirection(entity, glade.getMapPoint(x, y), player.getMapPoint());
//                        }
                    } else if (entity instanceof Magician) {
                        ((Moving) entity).moveInRandomDirection();
                    }
                }
            }
        }
    }

    public boolean naveExists() {
        for(int c = 0; c < 5; c++) {
            Country country = world.getCountry(c);
            for (int i = 0; i < Country.MAX_MAP_SIZE; i++) {
                for (int j = 0; j < Country.MAX_MAP_SIZE; j++) {
                    Entity entity = country.getMapPoint(i, j).getEntity();
                    if(entity instanceof Nave) {
                        return true;
                    }
                }
            }
        }

        return false;
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
        updatedCity = city;
        updatedWarrior = war;
        getPlayer().changeMoney(getPlayer().getSalary());
    }

    public void weekUpdate() {
        if (days > 0) {
            days--;
        } else {
            days = WEEK_LENGTH;
            weeks--;
            weekFinish();
        }
    }

    public MapPoint move(int dx, int dy) {
        int x = player.getX();
        int y = player.getY();
        if (x + dx < 2 || x + dx > 62 || y + dy < 2 || y + dy > 62) {
            return player.getCountry().getMapPoint(x, y);
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
                    Entity nave = player.getMapPoint().getEntity();
                    player.getMapPoint().setEntity(null);
                    player.move(x + dx, y + dy);
                    player.getMapPoint().setEntity(nave);
                }
            } else {
                if (mp.getLand() == R.drawable.land || mp.getLand() == R.drawable.plot
                        || mp.getLand() == R.drawable.sand) {
                    player.move(x + dx, y + dy);
                }
            }
        } else {
//            actionWithObject(mp);
            return mp;
        }

        moveEntities();
//        weekUpdate();
        player.tryActivateCaptains();
        return mp;
    }

    public void createNave(Country country, int x, int y) {
        country.getMapPoint(x, y).setEntity(new Nave());
    }

    public void destroyNave() {
        for(int c = 0; c < 5; c++) {
            Country country = world.getCountry(c);
            for (int i = 0; i < Country.MAX_MAP_SIZE; i++) {
                for (int j = 0; j < Country.MAX_MAP_SIZE; j++) {
                    if(country.getMapPoint(i, j).getEntity() instanceof Nave) {
                        country.getMapPoint(i, j).setEntity(null);
                    }
                }
            }
        }
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

    public void changeCountry(int n) {
        Country country = getWorld().getCountry(n);
        country.createMaps();
        player.changeCountry(country);
    }

    public int getDays() {
        return days;
    }

    public City getUpdatedCity() {
        return updatedCity;
    }

    public Warrior getUpdatedWarrior() {
        return updatedWarrior;
    }
}
