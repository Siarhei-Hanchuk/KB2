package by.siarhei.kb2.app.server.models.battle;

import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Mover;
import by.siarhei.kb2.app.server.warriors.Warrior;

public class WarriorEntity implements Entity, Warrior {
    private final Warrior warrior;
    private MapPoint point;
    private boolean friendly;
    public int step;
    private int count;
    private int defence;

    public WarriorEntity(Warrior warrior, int count, boolean friendly) {
        point.setEntity(this);
        this.point = point;
        this.warrior = warrior;
        this.count = count;
        this.friendly = friendly;
        this.defence = warrior.getDefence() * count;
        resetStep();
    }

    @Override
    public int getID() {
        return warrior.getId();
    }

    @Override
    public String getTextId() {
        return warrior.getTextId();
    }

    @Override
    public int getId() {
        return warrior.getId();
    }

    @Override
    public int getPriceInShop() {
        return -1;
    }

    @Override
    public int getCountInShop() {
        return -1;
    }

    @Override
    public int getDefence() {
        return warrior.getDefence();
    }

    @Override
    public int getDamage() {
        return warrior.getDamage();
    }

    @Override
    public boolean isShoot() {
        return warrior.isShoot();
    }

    @Override
    public boolean isFly() {
        return warrior.isFly();
    }

    public boolean isPlayerEntity() {
        return friendly;
    }

    @Override
    public int getStep() {
        return step;
    }

    void reduceStep(int step) {
        if (!warrior.isFly())
            this.step -= step;
    }

    void attack(WarriorEntity warrior) {
        warrior.takeAttack(this.warrior.getDamage() * count);
        step = 0;
    }

    private void takeAttack(int damage) {
        defence -= damage;
        count = defence / warrior.getDefence();
        // TODO:
//        if (count < 1)
//            destroy();
    }

    public int getCount() {
        return count;
    }

    void resetStep() {
        if (isFly())
            this.step = 6;
        else
            this.step = warrior.getStep();
    }

    boolean flyTo(MapPoint point) {
//        TODO:
//        int x = point.getX();
//        int y = point.getY();
//
//        for (int i = (x - 1 < 0) ? 0 : x - 1; i < ((x + 2 <= XSize) ? x + 2 : XSize); i++) {
//            for (int j = (y - 1 < 0) ? 0 : y - 1; j < ((y + 2 <= YSize) ? y + 2 : YSize); j++) {
//                if (mover.teleport(this, point.getGlade().getMapPoint(i, j))) {
//                    return true;
//                }
//            }
//        }
//        return false;
        step = 0;
        return false;
    }
}
