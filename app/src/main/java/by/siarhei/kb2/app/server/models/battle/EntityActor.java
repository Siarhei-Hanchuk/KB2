package by.siarhei.kb2.app.server.models.battle;

import by.siarhei.kb2.app.server.models.Mover;

class EntityActor {
    private final MapPointBattle from;
    private final MapPointBattle to;
    private final BattleField battleField;
    private final Mover mover;
    private final WarriorEntity entity;

    // TODO: remove forPlayer
    public EntityActor(BattleField battleField, MapPointBattle from, MapPointBattle to) {
        this.mover = new Mover(battleField);

        this.battleField = battleField;
        this.from = from;
        this.to = to;
        this.entity = from.getEntity();
    }

    public void tryMoveTo() {
        System.out.println("t1");
        if (!to.isLand() || (to.isEntity() && to.getEntity().isOwn(entity))) {
            return;
        }
        System.out.println("t2");
        if (to.getEntity() == null) {
            System.out.println("t3a");
            moveTo();
        } else {
            System.out.println("t3b");
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

    private WarriorEntity getEntity() {
        return entity;
    }

    private boolean isAbleToAttack() {
        return battleField.distance(from, to) == 1 || getEntity().isShoot();
    }
}
