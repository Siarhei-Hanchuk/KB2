package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.warriors.WarriorSquad;

public interface Fighting {
    WarriorSquad getWarriorSquad(int n);

    int getAuthority();

    void generateArmy(int authority, int group);
}
