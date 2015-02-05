package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.warriors.Warrior;
import com.neschur.kb2.app.warriors.WarriorFactory;
import com.neschur.kb2.app.warriors.WarriorSquad;

import java.util.Random;

public class Captain extends EntityImpl implements Fighting {
    private static final int MAX_ARMY = 5;
    private WarriorSquad[] warriors;
    private int authority;

    public Captain(MapPoint point) {
        super(point);
    }

    @Override
    public int getID() {
        return R.drawable.captain;
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
        warriors = new WarriorSquad[squadCount];
        for (int i = 0; i < squadCount; i++) {
            Warrior warrior = WarriorFactory.createRandomFromGroup(group);
            WarriorSquad squad = new WarriorSquad(warrior, authority / warrior.getDamage());
            warriors[i] = squad;
        }
    }
}
