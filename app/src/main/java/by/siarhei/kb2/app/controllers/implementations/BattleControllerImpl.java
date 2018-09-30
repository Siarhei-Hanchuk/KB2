package by.siarhei.kb2.app.controllers.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import by.siarhei.kb2.app.View;
import by.siarhei.kb2.app.controllers.ApplicationController;
import by.siarhei.kb2.app.controllers.BattleController;
import by.siarhei.kb2.app.controllers.ViewController;
import by.siarhei.kb2.app.server.entities.Fighting;
import by.siarhei.kb2.app.server.Game;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Player;
import by.siarhei.kb2.app.server.models.battle.BattleField;
import by.siarhei.kb2.app.server.models.battle.MapPointBattle;
import by.siarhei.kb2.app.server.warriors.WarriorSquad;

public class BattleControllerImpl extends ApplicationController implements BattleController {
    private final BattleField battleField;
    private final View battleView;
    private final Fighting fighting;

    public BattleControllerImpl(ViewController controller, Fighting fighting) {
        this.battleField = new BattleField(controller.getGame().getPlayer(), fighting, this);
        this.fighting = fighting;
        this.battleView = getViewFactory().getBattleView(this);
        setContentView(battleView);
    }

    @Override
    public MapPointBattle[][] getMap() {
        return battleField.getMapPoints();
    }

    @Override
    public void select(int x, int y) {
        // TODO - check
//        battleField.select(x, y);
//        updateView();
    }

    @Override
    public int getSelectedX() {
        return battleField.getSelectedX();
    }

    @Override
    public int getSelectedY() {
        return battleField.getSelectedY();
    }

    private void applyCasualties(HashMap<String, Integer> casualties) {
        // i though it should be placed in player entity
        // but it will result with player model hash change
        // and old version's saves will be broken. So it will be here
        Player player = getGame().getPlayer();
        ArrayList<WarriorSquad> newWarriorSquadList = new ArrayList<>();
        for (int i = 0; i < Player.MAX_ARMY; i++) {
            WarriorSquad warriorSquad = player.getWarriorSquad(i);
            if (warriorSquad != null) {
                String warriorTextId = warriorSquad.getWarrior().getTextId();
                if (casualties.keySet().contains(warriorTextId)){
                    warriorSquad.changeCount(-casualties.get(warriorTextId));
                    if (warriorSquad.getCount() > 0) {
                        newWarriorSquadList.add(warriorSquad);
                    }
                } else { //squad was not harmed, copy as is
                    newWarriorSquadList.add(warriorSquad);
                }
            }
        }
        player.clearArmy();
        for (WarriorSquad warriorSquad: newWarriorSquadList){
            player.pushArmy(warriorSquad.getWarrior(), warriorSquad.getCount());
        }
    }

    @Override
    public void battleFinish(boolean win, HashMap<String, Integer> casualties) {
        int authority = fighting.getAuthority() / 40; //??
        int money = fighting.getAuthority() * 40; //??
        if (win) {
            getGame().getPlayer().changeAuthority(authority);
            getGame().getPlayer().changeMoney(money);
            applyCasualties(casualties);
            fighting.defeat();
        } else {
            getGame().getPlayer().changeAuthority(-authority);
            MapPoint land = getGame().getPlayer().getCountry().getRandomLand();
            getGame().getPlayer().move(land.getX(), land.getY());
            getGame().getPlayer().clearArmy();
            getGame().getPlayer().deactivateCaptains();
        }
        setContentView(getViewFactory().getViewBattleResultsView(
                this, casualties, win, authority, money));
    }

    @Override
    public void updateView() {
        updateView(0);
    }

    @Override
    public void updateView(int delay) {
        battleView.refresh(delay);
    }

    @Override
    public void viewClose() {
//        new MainViewControllerImpl();
    }

    // TODO - remove
    @Override
    public Game getGame() {
        return null;
    }
}
