package by.siarhei.kb2.app.server.models.battle;

import by.siarhei.kb2.app.server.warriors.Warrior;

public class MoveArea {
    private final int XSize = 6;
    private final int YSize = 5;

    private BattleField battleField;

    public MoveArea(BattleField battleField) {
        this.battleField = battleField;
    }

    private void moveAreaFly() {
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
                MapPointBattle mapPoint = battleField.getMapPoint(x, y);
                if (mapPoint.isLand() || (mapPoint.isEntity() && !mapPoint.isPlayerEntity())) {
                    mapPoint.setMovable(true);
                }
            }
        }
    }

    public void build(MapPointBattle mapPoint) {
        clearMoveArea();
        Warrior war = mapPoint.getEntity();
        if (war.isFly())
            moveAreaFly();
        else {
            snake(mapPoint.getX(), mapPoint.getY(), war.getStep());
        }
        if (war.isShoot())
            shotGoals();
    }

    public void clearMoveArea() {
        for (int i = 0; i < XSize; i++) {
            for (int j = 0; j < YSize; j++) {
                battleField.getMapPoint(i, j).setMovable(false);
            }
        }
    }

    private void shotGoals() {
        for (int x = 0; x < XSize; x++) {
            for (int y = 0; y < YSize; y++) {
                MapPointBattle mapPoint = battleField.getMapPoint(x, y);

                if (mapPoint.isEntity() && !mapPoint.isPlayerEntity()) {
                    mapPoint.setMovable(true);
                }
            }
        }
    }

    private void snake(int x, int y, int step) {
        snake(x, y, step, true);
    }

    private void snake(int x, int y, int step, boolean ignoreEntity) {
        if (step < 0 || x < 0 || x > (XSize - 1) || y < 0 || y > (YSize - 1) ||
                !battleField.getMapPoint(x, y).isLand())
            return;
        step--;
        MapPointBattle mapPoint = battleField.getMapPoint(x, y);
        mapPoint.setMovable(true);
        if (!mapPoint.isEntity() || ignoreEntity) {
            snake(x + 1, y, step, false);
            snake(x - 1, y, step, false);
            snake(x, y + 1, step, false);
            snake(x, y - 1, step, false);
            snake(x + 1, y + 1, step, false);
            snake(x - 1, y - 1, step, false);
            snake(x - 1, y + 1, step, false);
            snake(x + 1, y - 1, step, false);
        }
    }

}
