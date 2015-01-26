package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.controllers.MainController;
import com.neschur.kb2.app.models.GameGrid;
import com.neschur.kb2.app.ui.ImageCache;

public class MainView extends View {
    private MainController mainController;
    private int xOffset = 0;
    private int yOffset = 0;

    public MainView(Context context, MainController mainController) {
        super(context, null, null);
        this.mainController = mainController;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        int x = (int) event.getX() - xOffset;
        int y = (int) event.getY() - yOffset;
        if (x > this.stepX() * 5) {
            int item = y / stepY();
            mainController.touchMenu(item);
        } else {
            int height_2_5 = stepY() * 2;
            int height_3_5 = stepY() * 3;
            int width_2_5 = stepX() * 2;
            int width_3_5 = stepX() * 3;
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

    public void calcOffsets() {
        double scaleX = (double) getWidth() / (96 * 6);
        double scaleY = (double) getHeight() / (82 * 5);
        if (scaleX > scaleY) {
            xOffset = (getWidth() - stepX() * 6) / 2;
        } else {
            yOffset = (getHeight() - stepX() * 5) / 2;
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        GameGrid grid = mainController.getGameGrid();
        imageCache = ImageCache.getInstance(getResources(), stepX(), stepY());
        calcOffsets();

        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (x < 5) {
                    Bitmap background = imageCache.getImage(
                            grid.getBackgroundBuyXY(x, y));
                    canvas.drawBitmap(background, xOffset + x * stepX(), yOffset + y * stepY(), null);
                }
                if (grid.getImageBuyXY(x, y) > -1) {
                    Bitmap image = imageCache.getImage(
                            grid.getImageBuyXY(x, y));
                    canvas.drawBitmap(image, xOffset + x * stepX(), yOffset + y * stepY(), null);
                }
            }
        }
    }
}
