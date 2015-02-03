package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.MainMenuController;

class MainMenuView extends ViewImpl {
    private final MainMenuController mainController;
    private boolean saved = false;

    public MainMenuView(Context context, MainMenuController mainController) {
        super(context, mainController);
        this.mainController = mainController;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Paint paint = getDefaultPaint();
        canvas.drawColor(Color.BLACK);

        if (mainController.isCurrentGame()) {
            canvas.drawText(i18n.translate(R.string.mainMenu_resume),
                    0, menuItemHeight(), paint);
        }
        canvas.drawText(i18n.translate(R.string.mainMenu_new_game),
                0, 2 * menuItemHeight(), paint);
        canvas.drawText(i18n.translate(R.string.mainMenu_training),
                0, 3 * menuItemHeight(), paint);
        canvas.drawText(i18n.translate(R.string.mainMenu_load_game),
                0, 4 * menuItemHeight(), paint);
        if (mainController.isCurrentGame()) {
            if (!saved) {
                canvas.drawText(i18n.translate(R.string.mainMenu_save_game),
                        0, 5 * menuItemHeight(), paint);
            } else {
                canvas.drawText(i18n.translate(R.string.mainMenu_save_game) + " - " +
                                i18n.translate(R.string.mainMenu_save_game_saved),
                        0, 5 * menuItemHeight(), paint);
                saved = false;
            }
        }
        canvas.drawText(i18n.translate(R.string.mainMenu_exit),
                0, 6 * menuItemHeight(), paint);
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
            }

        }
        return super.onTouchEvent(event);
    }
}
