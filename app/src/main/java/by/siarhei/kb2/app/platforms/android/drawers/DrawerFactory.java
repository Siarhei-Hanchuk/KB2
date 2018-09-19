package by.siarhei.kb2.app.platforms.android.drawers;

import android.graphics.Canvas;

import by.siarhei.kb2.app.platforms.android.XMainView;
import by.siarhei.kb2.app.server.GameDispatcher;

public class DrawerFactory {
    private final Canvas canvas;
    private final XMainView mainView;

    public DrawerFactory(Canvas canvas, XMainView mainView) {
        this.canvas = canvas;
        this.mainView = mainView;
    }

    public Drawer getDrawer(int viewMode) {
        switch (viewMode) {
            case GameDispatcher.VIEW_MODE_GRID:
                return new GameDrawer(canvas, mainView);
            case GameDispatcher.VIEW_MODE_MESSAGE:
                return new MessageDrawer(canvas, mainView);
            case GameDispatcher.VIEW_MODE_MENU:
                return new MenuDrawer(canvas, mainView);
            case GameDispatcher.VIEW_MODE_MENU2:
                return new Menu2Drawer(canvas, mainView);
            case GameDispatcher.VIEW_MODE_WEEK_FINISHED:
                return new WeekFinishedDrawer(canvas, mainView);
            case GameDispatcher.VIEW_MODE_ARMY_SHOP:
                return new ArmyShopDrawer(canvas, mainView);
            case GameDispatcher.VIEW_MODE_STATUS:
                return new StatusDrawer(canvas, mainView);
            case GameDispatcher.VIEW_MODE_PLAYER_ARMY:
                return new PlayerArmyDrawer(canvas, mainView);

            default:
                return null;
        }
    }
}
