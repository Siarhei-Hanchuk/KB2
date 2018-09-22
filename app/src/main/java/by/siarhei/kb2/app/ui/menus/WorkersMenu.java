package by.siarhei.kb2.app.ui.menus;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.server.Game;

public class WorkersMenu extends Menu {
    WorkersMenu(Game game, I18n i18n) {
        super(game, i18n);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public String getItemDescription(int i) {
        return i18n.translate("workers_item" + (i + 1)) + ": " + player.getWorker(i);
    }

    @Override
    public int getItemImageId(int i) {
        return 0;
    }

    @Override
    public boolean select(int i) {
        if(i < 4)
            game.selectWorker(i);
        return true;
    }
}
