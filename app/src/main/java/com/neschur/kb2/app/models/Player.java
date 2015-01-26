package com.neschur.kb2.app.models;

import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.entities.Nave;
import com.neschur.kb2.app.warriors.Warrior;
import com.neschur.kb2.app.warriors.WarriorSquad;

import java.io.Serializable;

public class Player implements Serializable {
    public static final int MAX_ARMY = 10;

    private boolean wallkick;
    private int workers[] = new int[4];
    private int money;
    private int authority;
    private Country country;
    private int usedMagicianCount[] = {0, 0, 0, 0, 0};
    private int magicPower;
    private int magicMaxCount;
    private int availableCountry = 1;
    private Nave nave;
    private boolean importantDocs[] = {false, false, false, false, false};
    private int salary = 0;
    private Memory memory;
    private int x;
    private int y;
    private WarriorSquad[] warriors = new WarriorSquad[MAX_ARMY]; // TODO - List

    public Player(Country _country) {
        memory = new Memory();
        country = _country;

        move(5, 5);

        easy();
        debug();
    }

    private void easy() {
        wallkick = false;
        money = 20000;
        authority = 50;
        magicPower = 2;
        for (int i = 0; i < 10; i++) {
            workers[0] = 4;
            workers[1] = 6;
            workers[2] = 0;
            workers[3] = 3;
        }
        salary = 500;
        magicMaxCount = 4;
    }

    private void debug() {
        availableCountry = 5;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        if (getNave() != null) {
            getNave().move(x, y);
        }
        memory.update(getCountry().getId(), x, y);
    }

    public void changeCountry(Country country) {
        this.country = country;
        x = 2;
        y = 2;
        if (inNave()) {
            nave.move(2, 2, country);
        }
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

    public boolean isWallkick() {
        return wallkick;
    }

    public void setWallkick() {
        this.wallkick = true;
    }

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public boolean inNave() {
        return nave != null;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void upMagicPower() {
        this.magicPower++;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void changeAuthority(int d) {
        authority += d;
    }

    public void upAvailableCountry() {
        if (availableCountry < 5)
            availableCountry++;
    }

    public int getAvailableCountry() {
        return availableCountry;
    }

    public int getUsedMagicianCount() {
        return usedMagicianCount[getCountry().getId()];
    }

    public void upUsedMagicianCount() {
        this.usedMagicianCount[getCountry().getId()]++;
    }

    public void changeWorker(int id, int count) {
        workers[id] += count;
    }

    public int getWorker(int id) {
        return workers[id];
    }

    public boolean getImportantDocs(int id) {
        return importantDocs[id];
    }

    public void setImportantDocs(int id) {
        this.importantDocs[id] = true;
    }

    public int getSalary() {
        return salary;
    }

    public void changeSalary(int delta) {
        this.salary += delta;
    }

    public int getMagicMaxCount() {
        return magicMaxCount;
    }

    public void upMagicMaxCount() {
        this.magicMaxCount += 1;
    }

    public WarriorSquad findWarriorSquad(Warrior warrior) {
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
}
