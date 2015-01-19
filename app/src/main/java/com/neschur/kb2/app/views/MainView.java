package com.neschur.kb2.app.views;

import android.content.Context;
import android.view.MotionEvent;

import com.neschur.kb2.app.controllers.MainController;

/**
 * Created by siarhei on 1.7.14.
 */
public class MainView extends View {
    private MainController mainController;

    public MainView(Context context, MainController mainController) {
        super(context, null, null);
        this.mainController = mainController;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getX() > this.getWidth() * 5 / 6) {
            int item = (int) event.getY() / (this.getHeight() / 5);
            mainController.touchMenu(item);
        } else {
            int height_2_5 = this.getHeight() * 2 / 5;
            int height_3_5 = this.getHeight() * 3 / 5;
            int width_2_5 = this.getWidth() * 2 / 5;
            int width_3_5 = this.getWidth() * 3 / 5;
            double y = event.getY();
            double x = event.getX();
            if (y > height_3_5) {
                if (x > width_2_5 && x < width_3_5) {
                    mainController.touchDown();
                }
                if (x < width_2_5) {
                    mainController.touchDownLeft();
                }
                if (x > width_3_5) {
                    mainController.touchDownRight();
                }
            }
            if (y < height_2_5) {
                if (x > width_2_5 && x < width_3_5) {
                    mainController.touchUp();
                }
                if (x < width_2_5) {
                    mainController.touchUpLeft();
                }
                if (x > width_3_5) {
                    mainController.touchUpRight();
                }
            }
            if (x > width_3_5 && y > height_2_5 && y < height_3_5) {
                mainController.touchRight();
            }
            if (x < width_2_5 && y > height_2_5 && y < height_3_5) {
                mainController.touchLeft();
            }
        }
        drawThread.refresh();
        return super.onTouchEvent(event);
    }
}
