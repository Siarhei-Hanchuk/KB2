package by.siarhei.kb2.app.actions;

import by.siarhei.kb2.app.entities.GoldChest;
import by.siarhei.kb2.app.models.Game;
import by.siarhei.kb2.app.models.Player;

public class GoldChestAction implements Action {
    private int action = -1;

    public GoldChestAction(int action) {
        this.action = action;
    }

    public void call(Game game, GoldChest chest) {
        Player player = game.getPlayer();

        switch (action) {
            case 0:
                player.changeMoney(+chest.getGold());
                break;
            case 1:
                player.changeAuthority(+chest.getAuthority());
                break;
            default:
                throw new java.lang.Error(String.format("invalid action: %s", action));
        }
    }
}
