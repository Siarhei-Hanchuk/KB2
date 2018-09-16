package by.siarhei.kb2.app.server.entities;

import by.siarhei.kb2.app.server.warriors.WarriorSquad;

public interface Fighting {
    WarriorSquad getWarriorSquad(int n);

    int getAuthority();

    void generateArmy(int authority, int group);

    void defeat();
}
