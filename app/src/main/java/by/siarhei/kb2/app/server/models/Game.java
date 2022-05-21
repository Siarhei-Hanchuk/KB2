package by.siarhei.kb2.app.server.models;

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
import by.siarhei.kb2.app.server.models.battle.Battle;
import by.siarhei.kb2.app.server.models.battle.BattleResult;
import by.siarhei.kb2.app.server.models.battle.Battler;
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
    transient private Battler battler;

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

    private void moveEntities() {
        HashMap<Entity, Boolean> movedEntity = new HashMap<>();
        Glade glade = player.getCountry();
        Mover mover = new Mover(glade);
        for (int x = player.getX() - 2; x <= player.getX() + 2; x++) {
            for (int y = player.getY() - 2; y <= player.getY() + 2; y++) {
                MapPoint mapPoint = glade.getMapPoint(x, y);
                Entity entity = glade.getMapPoint(x, y).getEntity();
                if (entity != null) {
                    if(movedEntity.get(entity) != null) {
                        continue;
                    }
                    movedEntity.put(entity, true);
                    if ((entity instanceof Captain && player.isCaptainsActivated())
                            || entity instanceof Sorcerer) {
                        // TODO - fix
                        if(distanceBetween(player.getMapPoint(), mapPoint) <= 1) {
//                            actionWithObject()
                        } else {
                            mover.moveInDirection(entity, glade.getMapPoint(x, y), player.getMapPoint());
                        }
                    } else if (entity instanceof Magician) {
                        mover.moveInRandomDirection(entity, mapPoint);
                    }
                }
            }
        }
    }

    private double distanceBetween(MapPoint mp1, MapPoint mp2) {
        double x = Math.pow(mp1.getX() - mp2.getX(), 2);
        double y = Math.pow(mp1.getY() - mp2.getY(), 2);

        return Math.sqrt(x + y);
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

    private void updateRandomArmyShop() {
        Iterator<ArmyShop> shopsIterator = world.getArmyShops();
        Warrior war = WarriorFactory.createRandom();
        while (shopsIterator.hasNext()) {
            if (shopsIterator.next().getID() == war.getId())
                shopsIterator.next().resetCount();
        }
        updatedWarrior = war;
    }

    private void updateRandomCity() {
        MapPoint cityMapPoint = null;
        Iterator<MapPoint> citiesIterator = world.getMapPointsList(City.class);
        int n = (new Random()).nextInt(25);
        for (int i = 0; citiesIterator.hasNext(); i++) {
            cityMapPoint = citiesIterator.next();
            if (i == n) {
                ((City) cityMapPoint.getEntity()).resetWorkers();
                break;
            }
        }
        updatedCity = ((City) cityMapPoint.getEntity());
    }

    private void weekFinish() {
        updateRandomArmyShop();
        updateRandomCity();
        getPlayer().changeMoney(getPlayer().getSalary());

        generateSpellMap();
    }

    private void generateSpellMap() {
        for (int i = 0; i < 5; i++) {
            world.getCountry(i).updateSpells();
        }
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
            return mp;
        }

        moveEntities();
        player.tryActivateCaptains();
//        moveCaptains();
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
        if(player.inNave()) {
            Entity nave = player.getMapPoint().getEntity();
            player.getMapPoint().setEntity(null);
            player.changeCountry(country, 2, 2);
            player.getMapPoint().setEntity(nave);
        } else {
            MapPoint mp = world.getCountry(n).getRandomLand();
            player.changeCountry(country, mp.getX(), mp.getY());
        }
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

    public void startBattle(MapPoint mp) {
        battler = new Battler(this);
        battler.startBattle(mp);
    }

    public Battle getBattle() {
        if(battler == null) {
            return null;
        }

        return battler.getBattle();
    }

    public BattleResult finishBattle() {
        return battler.finishBattle();
    }

    public void movePlayerInRandomPoint() {
        MapPoint land = player.getCountry().getRandomLand();
        player.move(land.getX(), land.getY());
    }
}
