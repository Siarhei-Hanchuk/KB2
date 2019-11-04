package by.siarhei.kb2.app.server.models.battle;

import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Mover;
import by.siarhei.kb2.app.server.warriors.Warrior;

public class WarriorEntity implements Entity, Warrior {
    private final int XSize = 6;
    private final int YSize = 5;
    private final Mover mover;
    private final Warrior warrior;
    private MapPoint point;
    private boolean friendly;
    public int step;
    private int count;
    private int defence;

    public WarriorEntity(MapPoint point, Warrior warrior, int count, boolean friendly) {
        //TODO:
//        mover = new Mover(point.getGlade());
        mover = null;
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

//    @Override
//    public void destroy() {
//        point.setEntity(null);
//    }
//
//    @Override
//    public MapPoint getMapPoint() {
//        return point;
//    }
//
//    @Override
//    public void setMapPoint(MapPoint point) {
//        this.point = point;
//    }

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

    public void reduceStep(int step) {
        if (!warrior.isFly())
            this.step -= step;
    }

    public void attack(WarriorEntity warrior) {
        warrior.takeAttack(this.warrior.getDamage() * count);
        step = 0;
    }

    public void takeAttack(int damage) {
        defence -= damage;
        count = defence / warrior.getDefence();
        // TODO:
//        if (count < 1)
//            destroy();
    }

    public int getCount() {
        return count;
    }

    public void resetStep() {
        if (isFly())
            this.step = 6;
        else
            this.step = warrior.getStep();
    }

    public boolean flyTo(MapPoint point) {
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

//    public void moveInDirection(MapPoint point) {
//        mover.moveInDirection(this, point);
//        this.reduceStep(1);
//    }

}
