package by.siarhei.kb2.app.server.models.battle;

import by.siarhei.kb2.app.server.entities.Captain;
import by.siarhei.kb2.app.server.entities.Castle;
import by.siarhei.kb2.app.server.entities.Fighting;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.models.Player;

public class Battler {
    private final Player player;
    private by.siarhei.kb2.app.server.models.MapPoint fightingPoint;
    private Battle battle;
    private final Game game;

    public Battler(Game game) {
        this.player = game.getPlayer();
        this.game = game;
    }

    public void startBattle(by.siarhei.kb2.app.server.models.MapPoint fightingPoint) {
        this.fightingPoint = fightingPoint;
        BattleFieldBuilder fieldBuilder = new BattleFieldBuilder(player, (Fighting) fightingPoint.getEntity());
        MapPoint[][] mapPoints = fieldBuilder.build();
        BattleField battleField = new BattleField(mapPoints, (Fighting) fightingPoint.getEntity());
        battle = new Battle(battleField);


    }

    public Battle getBattle() {
        return battle;
    }

    public BattleResult finishBattle() {
        BattleResult battleResult = battle.getBattleResult();
        if(battleResult.isWinner()) {
            finishBattleWithWin(battleResult);
        } else {
            finishBattleWithFail(battleResult);
        }
        return battleResult;
    }

    private void finishBattleWithWin(BattleResult battleResult) {
        game.getPlayer().changeMoney(battleResult.getGold());
        game.getPlayer().changeAuthority(battleResult.getAuthority());
        if(fightingPoint.getEntity() instanceof Captain) {
            fightingPoint.setEntity(null);
        } else {
            ((Castle) fightingPoint.getEntity()).setStricken();
        }
    }

    private void finishBattleWithFail(BattleResult battleResult) {
        player.changeAuthority(battleResult.getAuthority());

        game.movePlayerInRandomPoint();
        game.getPlayer().clearArmy();
        game.getPlayer().deactivateCaptains();
    }
}
