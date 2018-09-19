package by.siarhei.kb2.app.server.entities;

import by.siarhei.kb2.app.R;

import java.util.Random;

import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Player;
import by.siarhei.kb2.app.server.warriors.Warrior;
import by.siarhei.kb2.app.server.warriors.WarriorFactory;
import by.siarhei.kb2.app.server.warriors.WarriorSquad;

public class Captain implements Entity, Fighting {
    private static final int MAX_ARMY = 5;
    private final WarriorSquad[] warriors = new WarriorSquad[MAX_ARMY];
    private int authority;

    @Override
    public int getID() {
        return R.drawable.captain_0;
    }

    @Override
    public int getID(Player player) {
        return player.isCaptainsActivated() ? R.drawable.captain : R.drawable.captain_0;
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
            WarriorSquad squad = new WarriorSquad(warrior, authority / warrior.getDefence());
            warriors[i] = squad;
        }
    }

    @Override
    public void defeat() {
        // TODO:
//        this.destroy();
    }
}
