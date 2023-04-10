package by.siarhei.kb2.app.server.models;

import by.siarhei.kb2.app.server.countries.Country;
import by.siarhei.kb2.app.server.entities.Nave;
import by.siarhei.kb2.app.server.warriors.Warrior;
import by.siarhei.kb2.app.server.warriors.WarriorFactory;
import by.siarhei.kb2.app.server.warriors.WarriorSquad;

import java.io.Serializable;

public class Player implements Serializable {
    public static final int MAX_ARMY = 10;
    private final WarriorSquad[] warriors = new WarriorSquad[MAX_ARMY]; // TODO - List
    private final int[] workers = new int[4];
    private final byte[] relationshipWithOwner = {0, 0, 0, 0, 0};
    private final Memory memory;
    private Magic magic;
    private MagicianStatus magicianStatus;
    private boolean wallDestroyer = false;
    private int money;
    private int authority;
    private Country country;
    private int availableCountry = 1;
    private Nave nave;
    private int salary = 0;
    private int x;
    private int y;
    private int captainsActivated;
    private int captainsActivatedTimeout;

    public Player(Country _country, int mode) {
        memory = new Memory();
        country = _country;

        MapPoint point;
        switch (mode) {
            case Game.MODE_TEST:
                memory.showAll();
                point = country.getMapPoint(5, 5);
                easy();
                availableCountry = 5;
                for (int i = 0; i < 3; i++) {
                    Warrior warrior = WarriorFactory.createRandomFromGroup(3);
                    WarriorSquad squad = new WarriorSquad(warrior, 100);
                    warriors[i] = squad;
                }
                break;
            case Game.MODE_TRAINING:
                point = country.getLandNearCity();
                easy();
                break;
            case Game.MODE_GAME:
                point = country.getRandomLand();
                hard();
                break;
            default:
                point = country.getRandomLand();
                easy();
        }

        move(point.getX(), point.getY());
    }

    private void easy() {
        money = 20000;
        captainsActivatedTimeout = Game.WEEK_LENGTH * 5;
        captainsActivated = captainsActivatedTimeout;
        authority = 50;
        workers[0] = 4;
        workers[1] = 6;
        workers[2] = 0;
        workers[3] = 3;
        salary = 500;
        magic = new Magic(1, 4);
        magicianStatus = new MagicianStatus();
    }

    private void hard() {
        money = 0;
        captainsActivatedTimeout = Game.WEEK_LENGTH;
        captainsActivated = captainsActivatedTimeout;
        authority = 50;
        workers[0] = 2;
        workers[1] = 2;
        workers[2] = 0;
        workers[3] = 0;
        salary = 500;
        magic = new Magic(0, 2);
        magicianStatus = new MagicianStatus();
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        memory.update(getCountry().getId(), x, y);
    }

    public void move(MapPoint point) {
        move(point.getX(), point.getY());
    }

    public void changeCountry(Country country, int x, int y) {
        this.country = country;
        this.x = x;
        this.y = y;
    }

    public Memory getMemory() {
        return memory;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean changeMoney(int d) {
        if (money + d >= 0) {
            money += d;
            return true;
        }
        return false;
    }

    public Integer getMoney() {
        return money;
    }

    public boolean hasWallDestroyer() {
        return wallDestroyer;
    }

    public void setWallDestroyer() {
        this.wallDestroyer = true;
    }

    private Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public boolean inNave() {
        return nave != null;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void changeAuthority(int d) {
        authority += d;
        if (authority < 0)
            authority = 0;
    }

    public void upAvailableCountry() {
        if (availableCountry < 5)
            availableCountry++;
    }

    public int getAvailableCountry() {
        return availableCountry;
    }

    public void changeWorker(int id, int count) {
        workers[id] += count;
    }

    public int getWorker(int id) {
        return workers[id];
    }

    public boolean getImportantDocs(int id) {
        return relationshipWithOwner[id] == 1;
    }

    public void setImportantDocs(int id) {
        if (relationshipWithOwner[id] == 0)
            relationshipWithOwner[id] = 1;
    }

    public void setBigEars(int id) {
        relationshipWithOwner[id] = 1;
    }

    public int getSalary() {
        return salary;
    }

    public void changeSalary(int delta) {
        this.salary += delta;
    }

    private WarriorSquad findWarriorSquad(Warrior warrior) {
        for (int i = 0; i < MAX_ARMY; i++) {
            if (warriors[i] != null &&
                    warriors[i].getWarrior().getId() == warrior.getId()) {
                return warriors[i];
            }
        }
        return null;
    }

    public int armyAfford(Warrior warrior) {
        WarriorSquad squad = findWarriorSquad(warrior);
        return authority / warrior.getDefence() - ((squad != null) ? squad.getCount() : 0);
    }

    public void pushArmy(Warrior warrior, int count) {
        WarriorSquad squad = findWarriorSquad(warrior);
        if (squad == null) {
            squad = new WarriorSquad(warrior, count);
            int i = 0;
            while (warriors[i] != null) {
                i++;
            }
            warriors[i] = squad;
        } else {
            squad.changeCount(+count);
        }

    }

    public int getWarriorSquadsCount() {
        int count = 0;
        for (int i = 0; i < MAX_ARMY; i++) {
            if (warriors[i] != null) {
                count++;
            }
        }
        return count;
    }

    public WarriorSquad getWarriorSquad(int n) {
        return warriors[n];
    }

    public void clearArmy() {
        for (int i = 0; i < MAX_ARMY; i++) {
            warriors[i] = null;
        }
    }

    private boolean hasArmy() {
        for (int i = 0; i < MAX_ARMY; i++) {
            if (warriors[i] != null)
                return true;
        }
        return false;
    }

    public Magic getMagic() {
        return magic;
    }

    public MapPoint getMapPoint() {
        return getCountry().getMapPoint(getX(), getY());
    }

    public boolean isCaptainsActivated() {
        return captainsActivated < 1;
    }

    public void deactivateCaptains() {
        captainsActivated = captainsActivatedTimeout;
    }

    public void tryActivateCaptains() {
        if(captainsActivated > 0)
            captainsActivated --;
        if(hasArmy())
            captainsActivated = 0;
    }

    public void fireArmy(int i) {
        warriors[i] = null;
        for (int j = i + 1; j < warriors.length; j++) {
            warriors[j-1] = warriors[j];
            warriors[j] = null;
        }
    }

    public MagicianStatus getMagicianStatus() {
        return magicianStatus;
    }
}
