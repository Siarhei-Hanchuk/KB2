package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.BuildConfig;
import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.Storage;
import by.siarhei.kb2.app.platforms.android.MainActivity;
import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.StorageImpl;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.Response;

public class MainMenuView extends RootView {
    private final byte ACTION_START_GAME = 1;
    private final byte ACTION_START_TRAINING = 2;
    private final byte ACTION_START_TEST_GAME = 3;
    private final byte ACTION_SAVE= 4;
    private final byte ACTION_LOAD= 5;
    private final byte ACTION_EXIT = 7;
    private final String DEFAULT_SAVE_NAME = "save0";

    private boolean saved = false;

    public MainMenuView(MainView mainView) {
        super(mainView);
    }

    @Override
    public void draw(@NonNull Canvas canvas, Response response) {
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);
        Paint paint = getDefaultPaint();

        int xOffset = painter.getXOffset();

        int yDelta = getWidth() / 2;

        if (Server.getServer() != null) {
            paintMenuItem(painter, paint, 0, 2, R.string.mainMenu_resume);
        }

        paintMenuItem(painter, paint, 0, 4, R.string.mainMenu_new_game);

        paintMenuItem(painter, paint, 0, 6, R.string.mainMenu_training);

        Storage storage = new StorageImpl(MainActivity.getActivity());
        if (storage.savedGameExists(DEFAULT_SAVE_NAME)) {
            painter.drawText(i18n.translate(R.string.mainMenu_load_game),
                    yDelta, 2 * menuItemHeight(), paint);
        }

        if (Server.getServer() != null) {
            if (!saved) {
                painter.drawText(i18n.translate(R.string.mainMenu_save_game),
                        yDelta , 4 * menuItemHeight(), paint);
            } else {
                painter.drawText(i18n.translate(R.string.mainMenu_save_game) + " - " +
                                i18n.translate(R.string.mainMenu_save_game_saved),
                        yDelta , 4 * menuItemHeight(), paint);
                saved = false;
            }
        }

        painter.drawText(i18n.translate(R.string.mainMenu_exit),
                yDelta , 6 * menuItemHeight(), paint);

        if (BuildConfig.DEBUG)
            painter.drawText("Test", 0, 8 * menuItemHeight(), paint);

    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        int yItem = (int)(event.getY() / menuItemHeight());
        int xItem = (int)(event.getX());
        boolean firstColumn = true;
        if(xItem > getWidth() / 2) {
            firstColumn = false;
        }
        if(firstColumn) {
            switch (yItem) {
                case 1:
                    return true;
                case 3:
                    menuAction(ACTION_START_GAME);
                    return true;
                case 5:
                    menuAction(ACTION_START_TRAINING);
                    return true;
                case 7:
                    if(BuildConfig.DEBUG) {
                        menuAction(ACTION_START_TEST_GAME);
                        return true;
                    }
                    break;
            }
        } else {
            switch (yItem) {
                case 1:
                    if (menuAction(ACTION_LOAD)){
                        MainActivity.showToast(i18n.translate("mainMenu_load_game_loaded"));
                    } else {
                        MainActivity.showToast(i18n.translate("mainMenu_load_game_notLoaded"));
                    }
                    break;
                case 3:
                    if (Server.getServer() != null) {
                        if (menuAction(ACTION_SAVE)){
                            MainActivity.showToast(i18n.translate("mainMenu_save_game_saved"));
                            saved = true;
                        }
                    }
                    break;
                case 5:
                    menuAction(ACTION_EXIT);
                    break;
            }
        }
        return false;
    }

    private void paintMenuItem(Painter painter, Paint paint, int x, int y, int stringId) {
        painter.drawText(i18n.translate(stringId),
                x, y * menuItemHeight(), paint);
    }

    private boolean menuAction(int item) {
        switch (item) {
            case ACTION_START_TEST_GAME:
                Server.create(Game.MODE_TEST);
                break;
            case ACTION_START_GAME:
                Server.create(Game.MODE_GAME);
                break;
            case ACTION_START_TRAINING:
                Server.create(Game.MODE_TRAINING);
                break;
            case ACTION_EXIT:
                MainActivity.getActivity().finish();
                System.exit(0);
                break;
            case ACTION_SAVE:
                Storage storage = new StorageImpl(MainActivity.getActivity());
                storage.saveGame(Server.dumpGame(), DEFAULT_SAVE_NAME);
                return true;
            case ACTION_LOAD:
                Server.loadGame(getStorage().loadGame(DEFAULT_SAVE_NAME));
                return true;
        }

        return true;
    }

    private Storage getStorage() {
        return new StorageImpl(MainActivity.getActivity());
    }
}
