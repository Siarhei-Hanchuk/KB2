package by.siarhei.kb2.app.server.models.battle;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.entities.Fighting;
import by.siarhei.kb2.app.server.models.Player;
import by.siarhei.kb2.app.server.warriors.WarriorSquad;

public class BattleFieldBuilder {
    private final int XSize = 6;
    private final int YSize = 5;

    private final Player player;
    private final Fighting fighting;
    private final MapPoint[][] map = new MapPoint[XSize][YSize];

    BattleFieldBuilder(Player player, Fighting fighting) {
        this.player = player;
        this.fighting = fighting;
    }

    public MapPoint[][] build() {
        prepareField();
        prepareArmy();

        return map;
    }

    private void prepareField() {
        for (int i = 0; i < XSize; i++) {
            for (int j = 0; j < YSize; j++) {
                map[i][j] = new MapPoint(i, j);
                map[i][j].setLand(R.drawable.land);
            }
        }
    }

    private void prepareArmy() {
        WarriorSquad[] initArmy = new WarriorSquad[YSize];
        int plSquadsIndex = 0;
        for (int squadIndex = 0; squadIndex < Player.MAX_ARMY && plSquadsIndex < YSize; squadIndex++) {
            if (player.getWarriorSquad(squadIndex) != null) {
                initArmy[plSquadsIndex] = player.getWarriorSquad(squadIndex);
                plSquadsIndex++;
            }
        }
        for (int i = 0; i < YSize; i++) {
            Entity entity;
            if (initArmy[i] != null) {
                entity = new Entity(
                        initArmy[i].getWarrior(),
                        initArmy[i].getCount(),
                        true);
                map[0][i].setEntity(entity);
            }
            if (fighting.getWarriorSquad(i) != null) {
                entity = new Entity(
                        fighting.getWarriorSquad(i).getWarrior(),
                        fighting.getWarriorSquad(i).getCount(),
                        false);
                map[5][i].setEntity(entity);
            }
        }
    }
}
