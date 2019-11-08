package by.siarhei.kb2.app.server.models.battle;

import by.siarhei.kb2.app.server.entities.Fighting;
import by.siarhei.kb2.app.server.models.Player;

public class Battler {
    private final Player player;

    private Battle battle;

    public Battler(Player player) {
        this.player = player;
    }

    public void startBattle(Fighting fighting) {
        BattleFieldBuilder fieldBuilder = new BattleFieldBuilder(player, fighting);
        MapPoint[][] mapPoints = fieldBuilder.build();
        BattleField battleField = new BattleField(mapPoints, fighting);
        battle = new Battle(battleField);
    }

    public Battle getBattle() {
        return battle;
    }

    public BattleResult finishBattle() {
        BattleResult battleResult = battle.getBattleResult();
        player.changeMoney(battleResult.getGold());
        player.changeAuthority(battleResult.getAuthority());
        return battleResult;

        //    @Override
        //    public void battleFinish(boolean win, HashMap<String, Integer> casualties) {
        //        int authority = fighting.getAuthority() / 40; //??
        //        int money = fighting.getAuthority() * 40; //??
        //        if (win) {
        //            getGame().getPlayer().changeAuthority(authority);
        //            getGame().getPlayer().changeMoney(money);
        //            applyCasualties(casualties);
        //            fighting.defeat();
        //        } else {
        //            getGame().getPlayer().changeAuthority(-authority);
        //            MapPoint land = getGame().getPlayer().getCountry().getRandomLand();
        //            getGame().getPlayer().move(land.getX(), land.getY());
        //            getGame().getPlayer().clearArmy();
        //            getGame().getPlayer().deactivateCaptains();
        //        }
        //        setContentView(getViewFactory().getViewBattleResultsView(
        //                this, casualties, win, authority, money));
        //    }
        //
    }
}
