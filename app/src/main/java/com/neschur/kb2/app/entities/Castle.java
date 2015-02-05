package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.warriors.Warrior;
import com.neschur.kb2.app.warriors.WarriorFactory;
import com.neschur.kb2.app.warriors.WarriorSquad;

public class Castle extends EntityImpl implements Fighting{
    private static final int ARMY_COUNT = 5;
    private final WarriorSquad[] warriors = new WarriorSquad[ARMY_COUNT];
    private int authority;

    public Castle(MapPoint point) {
        super(point);
    }

    @Override
    public int getID() {
        return R.drawable.castle_c;
    }

    @Override
    public WarriorSquad getWarriorSquad(int n) {
        return warriors[n];
    }

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
}
