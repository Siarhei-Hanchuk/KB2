package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.controllers.MainController;

/**
 * Created by siarhei on 25.01.15.
 */
public class MainMenuView extends View {
    private MainController mainController;
    private boolean saved = false;

    public MainMenuView(Context context, MainController mainController) {
        super(context, null, mainController);
        this.mainController = mainController;
    }

    public void draw(Canvas canvas) {
        Paint paint = getDefaultPaint();
        canvas.drawColor(Color.BLACK);

        if (mainController.isCurrentGame()) {
            canvas.drawText(I18n.translate(R.string.mainMenu_resume),
                    0, 1 * menuItemHeight(), paint);
        }
        canvas.drawText(I18n.translate(R.string.mainMenu_new_game),
                0, 2 * menuItemHeight(), paint);
        canvas.drawText(I18n.translate(R.string.mainMenu_training),
                0, 3 * menuItemHeight(), paint);
        canvas.drawText(I18n.translate(R.string.mainMenu_load_game),
                0, 4 * menuItemHeight(), paint);
        if (mainController.isCurrentGame()) {
            if (!saved) {
                canvas.drawText(I18n.translate(R.string.mainMenu_save_game),
                        0, 5 * menuItemHeight(), paint);
            } else {
                canvas.drawText(I18n.translate(R.string.mainMenu_save_game) + " - " +
                                I18n.translate(R.string.mainMenu_save_game_saved),
                        0, 5 * menuItemHeight(), paint);
                saved = false;
            }
        }
        canvas.drawText(I18n.translate(R.string.mainMenu_exit),
                0, 6 * menuItemHeight(), paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getX() < getWidth() / 2) {
            if (event.getY() < menuItemHeight() * 1) {
                if (mainController.isCurrentGame()) {
                    mainController.viewClose();
                }
            } else if (event.getY() < menuItemHeight() * 2) {
                mainController.newGame();
            } else if (event.getY() < menuItemHeight() * 3) {
                mainController.newTraining();
            } else if (event.getY() < menuItemHeight() * 4) {
                mainController.loadGame();
                drawThread.refresh();
            } else if (event.getY() < menuItemHeight() * 5) {
                if (mainController.isCurrentGame()) {
                    mainController.saveGame();
                    drawThread.refresh();
                    saved = true;
                }
            } else if (event.getY() < menuItemHeight() * 6) {
                mainController.exit();
            }

        }
        return super.onTouchEvent(event);
    }
}
