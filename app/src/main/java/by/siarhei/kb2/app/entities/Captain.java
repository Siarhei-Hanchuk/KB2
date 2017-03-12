package by.siarhei.kb2.app.entities;

import com.neschur.kb2.app.R;
import by.siarhei.kb2.app.models.MapPoint;
import by.siarhei.kb2.app.models.Mover;
import by.siarhei.kb2.app.warriors.Warrior;
import by.siarhei.kb2.app.warriors.WarriorFactory;
import by.siarhei.kb2.app.warriors.WarriorSquad;

import java.util.Random;

public class Captain extends EntityImpl implements Fighting, Moving {
    private static final int MAX_ARMY = 5;
    private final WarriorSquad[] warriors = new WarriorSquad[MAX_ARMY];
    private final Mover mover;
    private int authority;
    private boolean activated = false;

    public Captain(MapPoint point) {
        super(point);
        mover = new Mover(point.getGlade());
    }

    @Override
    public int getID() {
        return activated ? R.drawable.captain : R.drawable.captain_0;
    }

    @Override
    public WarriorSquad getWarriorSquad(int n) {
        return warriors[n];
    }

    @Override
    public int getAuthority() {
        return authority;
    }

    @Override
    public void generateArmy(int authority, int group) {
        this.authority = authority;
        int squadCount = (new Random()).nextInt(MAX_ARMY) + 1;
        for (int i = 0; i < squadCount; i++) {
            Warrior warrior = WarriorFactory.createRandomFromGroup(group);
            WarriorSquad squad = new WarriorSquad(warrior, authority / warrior.getDamage());
            warriors[i] = squad;
        }
    }

    @Override
    public void moveInDirection(MapPoint point) {
        mover.moveInDirection(this, point);
    }

    @Override
    public void moveInRandomDirection() {
        mover.moveInRandomDirection(this);
    }

    @Override
    public boolean canMoveTo(MapPoint point) {
        return mover.canMoveTo(point);
    }

    public void activate() {
        activated = true;
    }

    public boolean isActive() {
        return activated;
    }
}
