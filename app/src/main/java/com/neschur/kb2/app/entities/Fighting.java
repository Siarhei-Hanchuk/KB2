package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.warriors.WarriorSquad;

public interface Fighting {
    public WarriorSquad getWarriorSquad(int n);

    public int getAuthority();

    public void generateArmy(int authority, int group);
}
