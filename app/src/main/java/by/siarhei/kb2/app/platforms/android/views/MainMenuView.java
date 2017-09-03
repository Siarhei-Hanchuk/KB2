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
import by.siarhei.kb2.app.platforms.android.views.helpers.Painter;

class MainMenuView extends ViewImpl {
    private final MainMenuController mainController;
    private boolean saved = false;

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

        if (mainController.isCurrentGame()) {
            painter.drawText(i18n.translate(R.string.mainMenu_resume),
                    0, menuItemHeight(), paint);
        }
        painter.drawText(i18n.translate(R.string.mainMenu_new_game),
                0, 2 * menuItemHeight(), paint);
        painter.drawText(i18n.translate(R.string.mainMenu_training),
                0, 3 * menuItemHeight(), paint);
        painter.drawText(i18n.translate(R.string.mainMenu_load_game),
                0, 4 * menuItemHeight(), paint);
        if (mainController.isCurrentGame()) {
            if (!saved) {
                painter.drawText(i18n.translate(R.string.mainMenu_save_game),
                        0, 5 * menuItemHeight(), paint);
            } else {
                painter.drawText(i18n.translate(R.string.mainMenu_save_game) + " - " +
                                i18n.translate(R.string.mainMenu_save_game_saved),
                        0, 5 * menuItemHeight(), paint);
                saved = false;
            }
        }
        painter.drawText(i18n.translate(R.string.mainMenu_exit),
                0, 6 * menuItemHeight(), paint);

        if (BuildConfig.DEBUG)
            painter.drawText("Test", 0, 7 * menuItemHeight(), paint);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (event.getX() < getWidth() / 2) {
            if (event.getY() < menuItemHeight()) {
                if (mainController.isCurrentGame()) {
                    mainController.viewClose();
                }
            } else if (event.getY() < menuItemHeight() * 2) {
                mainController.newGame();
            } else if (event.getY() < menuItemHeight() * 3) {
                mainController.newTraining();
            } else if (event.getY() < menuItemHeight() * 4) {
                mainController.loadGame();
            } else if (event.getY() < menuItemHeight() * 5) {
                if (mainController.isCurrentGame()) {
                    mainController.saveGame();
                    saved = true;
                }
            } else if (event.getY() < menuItemHeight() * 6) {
                mainController.exit();
            } else if (event.getY() < menuItemHeight() * 7) {
                mainController.newTestGame();
            }

        }
        return super.onTouchEvent(event);
    }
}
