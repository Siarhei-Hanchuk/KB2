package by.siarhei.kb2.app.controllers.implementations;

import java.util.HashMap;
import by.siarhei.kb2.app.View;
import by.siarhei.kb2.app.controllers.ApplicationController;
import by.siarhei.kb2.app.controllers.ArmyShopViewController;
import by.siarhei.kb2.app.controllers.BattleAskController;
import by.siarhei.kb2.app.controllers.BattleController;
import by.siarhei.kb2.app.controllers.MagicViewController;
import by.siarhei.kb2.app.controllers.PlayerViewsController;
import by.siarhei.kb2.app.entities.ArmyShop;
import by.siarhei.kb2.app.entities.Entity;
import by.siarhei.kb2.app.entities.Fighting;
import by.siarhei.kb2.app.models.Player;
import by.siarhei.kb2.app.warriors.WarriorFactory;

public class PlayerViewsControllerImpl extends ApplicationController
        implements PlayerViewsController, MagicViewController, ArmyShopViewController,
        BattleAskController {
    private final View view;
    private Entity entity;

    public PlayerViewsControllerImpl(Object viewType) {
        if (viewType instanceof String) {
            view = getViewFactory().getPlayersView(this, (String) viewType);
        } else {
            entity = (Entity) viewType;
            view = getViewFactory().getViewForEntity(this, entity);
        }
        if (view != null)
            setContentView(view);
    }

    @Override
    public Player getPlayer() {
        return getGame().getPlayer();
    }

    @Override
    public void viewClose() {
        new MainViewControllerImpl();
    }

    @Override
    public void takeArmy(String id) {
        getPlayer().pushArmy(WarriorFactory.create(id), 1);
    }

    @Override
    public void buyArmy(ArmyShop shop, int count) {
        getGame().buyArmy(shop, count);
        view.refresh();
    }

    @Override
    public void startBattle() {
        BattleController controller = new BattleControllerImpl(this, (Fighting) entity);
        if(!getPlayer().hasArmy())
            controller.battleFinish(false, new HashMap<>());
    }
}
