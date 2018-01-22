package by.siarhei.kb2.app.ui.menus;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.models.Game;
import by.siarhei.kb2.app.warriors.WarriorSquad;

public class ArmyFireMenu extends Menu {
    ArmyFireMenu(Game game, I18n i18n) {
        super(game, i18n);
    }

    @Override
    public int getCount() {
        return player.getWarriorSquadsCount();
    }

    @Override
    public String getItemDescription(int i) {
        WarriorSquad squad = player.getWarriorSquad(i);
        return "" + i + ": " + i18n.translate("army_names_" + squad.getWarrior().getTextId());
    }

    @Override
    public int getItemImageId(int i) {
        return 0;
    }

    @Override
    public boolean select(int i) {
        player.fireArmy(i);
        return true;
    }
}
