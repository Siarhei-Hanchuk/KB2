package by.siarhei.kb2.app.server.entities;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.warriors.Warrior;
import by.siarhei.kb2.app.server.warriors.WarriorFactory;
import by.siarhei.kb2.app.server.warriors.WarriorSquad;

import java.util.Iterator;

public class Castle implements Entity, Fighting, CastlesOwner {
    private static final int ARMY_COUNT = 5;
    private final WarriorSquad[] warriors = new WarriorSquad[ARMY_COUNT];
    private final int nameId;
    private int authority;

    public Castle(int nameId) {
        this.nameId = nameId;
    }

    @Override
    public int getID() {
        return R.drawable.castle_c;
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
        for (int i = 0; i < ARMY_COUNT; i++) {
            Warrior warrior = WarriorFactory.createRandomFromGroup(group); // TODO
            WarriorSquad squad = new WarriorSquad(warrior, authority / warrior.getDamage());
            warriors[i] = squad;
        }
    }

    @Override
    public Iterator<Castle> getCastles() {
        final Castle self = this;

        return new Iterator<Castle>() {
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public Castle next() {
                hasNext = false;
                return self;
            }

            @Override
            public void remove() {
            }
        };
    }

    public int getNameId() {
        return nameId;
    }
}
