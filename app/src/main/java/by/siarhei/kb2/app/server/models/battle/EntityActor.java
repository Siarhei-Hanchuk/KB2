package by.siarhei.kb2.app.server.models.battle;

import by.siarhei.kb2.app.server.models.Mover;

class EntityActor {
    private final MapPoint from;
    private final MapPoint to;
    private final BattleField battleField;
    private final Mover mover;
    private final Entity entity;

    EntityActor(BattleField battleField, MapPoint from, MapPoint to) {
        this.mover = new Mover(battleField);

        this.battleField = battleField;
        this.from = from;
        this.to = to;
        this.entity = from.getEntity();
    }

    public void tryMoveTo() {
        if (!to.isLand()) {
            return;
        }
        if (to.isEntity() && to.getEntity().isOwn(entity)) {
            resetStep();
            return;
        }
        if (to.getEntity() == null) {
            moveTo();
        } else {
            attack();
        }
    }

    private void moveTo() {
        getEntity().reduceStep(battleField.distance(from, to));
        mover.teleport(getEntity(), from, to);
    }

    private void attack() {
        if (!to.isEntity() || to.getEntity().isOwn(entity)) return;

        if (isAbleToAttack()) {
            getEntity().attack(to.getEntity());
        }
    }

    private Entity getEntity() {
        return entity;
    }

    private boolean isAbleToAttack() {
        return battleField.distance(from, to) == 1 || getEntity().isShoot();
    }

    private void resetStep() {
        getEntity().reduceStep(getEntity().getStep());
    }
}
