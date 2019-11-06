package by.siarhei.kb2.app.server.models.battle.interactors;

import by.siarhei.kb2.app.server.models.battle.BattleField;
import by.siarhei.kb2.app.server.models.battle.Interactor;
import by.siarhei.kb2.app.server.models.battle.MapPointBattle;

public class AiInteractorImpl implements Interactor {
    private final BattleField battleField;
    public AiInteractorImpl(BattleField battleField) {
        this.battleField = battleField;
    }

    @Override
    public void action(MapPointBattle mapPointBattle) {

    }

    @Override
    public boolean finished() {
        return false;
    }
}
