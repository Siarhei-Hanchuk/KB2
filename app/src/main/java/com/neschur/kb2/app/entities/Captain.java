package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.warriors.WarriorSquad;

public class Captain extends Entity implements Fighting{
    public static final int MAX_ARMY = 5;
    private WarriorSquad[] warriors = new WarriorSquad[MAX_ARMY];

    public Captain(Country country, int x, int y) {
        super(country, x, y);
    }

    @Override
    public int getID() {
        return R.drawable.capitan;
    }

    public void setSquad(int n, WarriorSquad squad) {
        warriors[n] = squad;
    }

    @Override
    public WarriorSquad getWarriorSquad(int n) {
        return null;
    }
}
