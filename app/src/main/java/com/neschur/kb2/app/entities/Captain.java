package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.warriors.WarriorSquad;

public class Captain extends EntityImpl implements Fighting {
    private static final int MAX_ARMY = 5;
    private final WarriorSquad[] warriors = new WarriorSquad[MAX_ARMY];

    public Captain(MapPoint point) {
        super(point);
    }

    @Override
    public int getID() {
        return R.drawable.captain;
    }

    public void setSquad(int n, WarriorSquad squad) {
        warriors[n] = squad;
    }

    @Override
    public WarriorSquad getWarriorSquad(int n) {
        return warriors[n];
    }

    @Override
    public int getAuthority() {
        return 100; // TODO
    }
}
