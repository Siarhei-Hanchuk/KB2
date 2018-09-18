package by.siarhei.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.BuildConfig;
import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.controllers.MainMenuController;
import by.siarhei.kb2.app.platforms.android.MainActivity;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;

class MainMenuView extends ViewImpl {
    private final MainMenuController mainController;
    private boolean saved = false;

    private int xOffset;

    public MainMenuView(Context context, MainMenuController mainController) {
        super(context, mainController);
        this.mainController = mainController;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);

        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);
        Paint paint = getDefaultPaint();

        xOffset = painter.getXOffset();

        int yDelta = getWidth() / 2;

        if (mainController.isCurrentGame()) {
            paintMenuItem(painter, paint, 0, 2, R.string.mainMenu_resume);
        }

        paintMenuItem(painter, paint, 0, 4, R.string.mainMenu_new_game);

        paintMenuItem(painter, paint, 0, 6, R.string.mainMenu_training);

        painter.drawText(i18n.translate(R.string.mainMenu_load_game),
                yDelta , 2 * menuItemHeight(), paint);

        if (mainController.isCurrentGame()) {
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
            xItem = xItem - getWidth() / 2;
            firstColumn = false;
        }
        xItem = (xItem > 0) ? 1 : 0;
        xItem = xItem - xOffset;
        if(firstColumn) {
            switch (yItem) {
                case 1:
                    mainController.viewClose();
                    break;
                case 3:
                    mainController.newGame();
                    break;
                case 5:
                    mainController.newTraining();
                    break;
                case 7:
                    if(BuildConfig.DEBUG)
                        mainController.newTestGame();
                    break;
            }
        } else {
            switch (yItem) {
                case 1:
                    if (mainController.loadGame()){
                        MainActivity.showToast(i18n.translate("mainMenu_load_game_loaded"));
                    } else {
                        MainActivity.showToast(i18n.translate("mainMenu_load_game_notLoaded"));
                    }
                    break;
                case 3:
                    if (mainController.isCurrentGame()) {
                        if (mainController.saveGame()){
                            MainActivity.showToast(i18n.translate("mainMenu_save_game_saved"));
                            saved = true;
                        }
                    }
                    break;
                case 5:
                    mainController.exit();
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    private void paintMenuItem(Painter painter, Paint paint, int x, int y, int stringId) {
        painter.drawText(i18n.translate(stringId),
                x, y * menuItemHeight(), paint);
    }
}
