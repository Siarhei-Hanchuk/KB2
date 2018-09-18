package by.siarhei.kb2.app.server;

import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.ui.menus.Menu;

public class GameDispatcher {
    public GameDispatcher() {

    }

    public void request(Request data) {
        // TODO - move to request processor
        if(data.getAction() == Request.ACTION_MOVE) {
            MapPoint mp = game.move(data.getX(), data.getY());
            EntityActivator entityActivator = new EntityActivator(mp);
            entityActivator.activate();

        }
        if(data.getAction() == Request.ACTION_OK) {
            game.setViewMode(Game.VIEW_MODE_GRID);
        }
        if(data.getAction() == Request.ACTION_SELECT) {
            System.out.println("AAAL");
            Menu menu = game.getMenu();
            menu.select(data.getMenuItem());

            // TODO - check
            game.setViewMode(Game.VIEW_MODE_GRID);
        }
        if(data.getAction() == Request.ACTION_EXIT) {
            game.setViewMode(Game.VIEW_MODE_GRID);
        }

        return true;
    }
}
