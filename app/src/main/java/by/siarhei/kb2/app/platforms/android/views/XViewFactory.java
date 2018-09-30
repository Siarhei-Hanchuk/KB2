package by.siarhei.kb2.app.platforms.android.views;

import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.server.GameDispatcher;

public class XViewFactory {
    private final MainView mainView;

    public XViewFactory(MainView mainView) {
        this.mainView = mainView;
    }

    public RootView getView(int viewMode) {
        switch (viewMode) {
            case GameDispatcher.VIEW_MODE_GRID:
                return new GameView(mainView);
            case GameDispatcher.VIEW_MODE_MESSAGE:
                return new MessageView(mainView);
            case GameDispatcher.VIEW_MODE_MENU:
                return new MenuView(mainView);
            case GameDispatcher.VIEW_MODE_MENU2:
                return new Menu2View(mainView);
            case GameDispatcher.VIEW_MODE_WEEK_FINISHED:
                return new WeekFinishedView(mainView);
            case GameDispatcher.VIEW_MODE_ARMY_SHOP:
                return new ArmyShopView(mainView);
            case GameDispatcher.VIEW_MODE_STATUS:
                return new StatusView(mainView);
            case GameDispatcher.VIEW_MODE_PLAYER_ARMY:
                return new PlayerArmyView(mainView);
            case GameDispatcher.VIEW_MODE_MAP:
                return new MapView(mainView);
            case GameDispatcher.VIEW_MODE_MAGIC:
                return new MagicView(mainView);
            default:
                return null;
        }
    }
}
