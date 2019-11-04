package by.siarhei.kb2.app.server.models.battle;

import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Mover;

public class BattleAi {
    private final BattleField battleField;

    public BattleAi(BattleField battleField ) {
        this.battleField = battleField ;
    }

    public boolean isFinished() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (battleField.isEntity(x, y) && !battleField.isPlayerEntity(x, y) && battleField.getEntity(x, y).getStep() > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void move() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if(!battleField.isEntity(x, y) || battleField.isPlayerEntity(x, y)) {
                    continue;
                }
                WarriorEntity entity = battleField.getEntity(x, y);
                System.out.print(entity.getStep());
                System.out.print(" ");
                System.out.println(entity.getTextId());
                if (entity.getStep() > 0) {
                    battleField.setSelected(battleField.getMapPoint(x, y));
                    MapPoint mapPoint = battleField.getMapPoint(x, y);
                    step(mapPoint, battleField.getEntity(x, y));
                    return;
                }
            }
        }
    }

//    TODO - check
    private void step(MapPoint from, WarriorEntity war) {
        MapPointBattle attackedPoint = findUserWar(true);
        if (attackedPoint == null)
            attackedPoint = findNearestUserWar(from);
        if (war.isShoot()) {
            war.attack(attackedPoint.getEntity());
        } else if (war.isFly()) {
            if (distance(from, attackedPoint) == 1)
                war.attack(attackedPoint.getEntity());
            else
                war.flyTo(attackedPoint);
        } else {
            if (distance(from, attackedPoint) == 1 && war.getStep() > 0) {
                war.attack(attackedPoint.getEntity());
            } else {
                Mover mover = new Mover(this.battleField);
                mover.moveInDirection(war, from, attackedPoint);
                war.step--;
            }
        }
    }

    private MapPointBattle findUserWar(boolean shoot) {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (battleField.isEntity(x, y) && battleField.isPlayerEntity(x, y)) {
                    if (shoot) {
                        if (battleField.getEntity(x, y).isShoot())
                            return battleField.getMapPoint(x, y);
                    } else {
                        return battleField.getMapPoint(x, y);
                    }
                }
            }
        }
        return null;
    }

    private MapPointBattle findNearestUserWar(MapPoint from) {
        int distance = 99;
        MapPointBattle dst = null;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (battleField.isEntity(i, j) && battleField.isPlayerEntity(i, j)) {
                    int d = distance(from, battleField.getMapPoint(i, j));
                    if (d < distance) {
                        distance = d;
                        dst = battleField.getMapPoint(i, j);
                    }
                }
            }
        }
        return dst;
    }

    private int distance(MapPoint from, MapPoint point) {
        return Math.max(Math.abs(from.getX() - point.getX()),
                Math.abs(from.getY() - point.getY()));
    }
}
