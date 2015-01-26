package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
        canvas.drawColor(Color.BLACK);

        if (mainController.isCurrentGame()) {
            canvas.drawText(I18n.translate(R.string.mainMenu_resume),
                    0, 1 * ITEM_SIZE, defaultPaint);
        }
        canvas.drawText(I18n.translate(R.string.mainMenu_new_game),
                0, 3 * ITEM_SIZE, defaultPaint);
        canvas.drawText(I18n.translate(R.string.mainMenu_training),
                0, 5 * ITEM_SIZE, defaultPaint);
        canvas.drawText(I18n.translate(R.string.mainMenu_load_game),
                0, 7 * ITEM_SIZE, defaultPaint);
        if (mainController.isCurrentGame()) {
            if (!saved) {
                canvas.drawText(I18n.translate(R.string.mainMenu_save_game),
                        0, 9 * ITEM_SIZE, defaultPaint);
            } else {
                canvas.drawText(I18n.translate(R.string.mainMenu_save_game) + " - " +
                                I18n.translate(R.string.mainMenu_save_game_saved),
                        0, 9 * ITEM_SIZE, defaultPaint);
                saved = false;
            }
        }
        canvas.drawText(I18n.translate(R.string.mainMenu_exit),
                0, 11 * ITEM_SIZE, defaultPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getX() < getWidth() / 2) {
            if (event.getY() < ITEM_SIZE * 2) {
                mainController.viewClose();
            } else if (event.getY() < ITEM_SIZE * 4) {
                mainController.newGame();
            } else if (event.getY() < ITEM_SIZE * 6) {
                mainController.newTraining();
            } else if (event.getY() < ITEM_SIZE * 8) {
                mainController.loadGame();
                drawThread.refresh();
            } else if (event.getY() < ITEM_SIZE * 10) {
                mainController.saveGame();
                drawThread.refresh();
                saved = true;
            } else if (event.getY() < ITEM_SIZE * 12) {
                mainController.exit();
            }

        }
        return super.onTouchEvent(event);
    }
}
