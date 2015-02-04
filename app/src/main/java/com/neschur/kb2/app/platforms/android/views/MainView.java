package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.controllers.MainViewController;
import com.neschur.kb2.app.models.GameGrid;

class MainView extends ViewImpl {
    private final MainViewController mainViewController;

    public MainView(Context context, MainViewController mainViewController) {
        super(context, mainViewController);
        this.mainViewController = mainViewController;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        int x = (int) event.getX() - xOffset;
        int y = (int) event.getY() - yOffset;
        if (x > this.stepX() * 5) {
            int item = (y / stepY());
            mainViewController.touchMenu(item);
        } else {
            int height_2_5 = stepY() * 2;
            int height_3_5 = stepY() * 3;
            int width_2_5 = stepX() * 2;
            int width_3_5 = stepX() * 3;
            if (y > height_3_5) {
                if (x > width_2_5 && x < width_3_5) {
                    mainViewController.touchDown();
                }
                if (x < width_2_5) {
                    mainViewController.touchDownLeft();
                }
                if (x > width_3_5) {
                    mainViewController.touchDownRight();
                }
            }
            if (y < height_2_5) {
                if (x > width_2_5 && x < width_3_5) {
                    mainViewController.touchUp();
                }
                if (x < width_2_5) {
                    mainViewController.touchUpLeft();
                }
                if (x > width_3_5) {
                    mainViewController.touchUpRight();
                }
            }
            if (x > width_3_5 && y > height_2_5 && y < height_3_5) {
                mainViewController.touchRight();
            }
            if (x < width_2_5 && y > height_2_5 && y < height_3_5) {
                mainViewController.touchLeft();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        GameGrid grid = mainViewController.getGameGrid();

        for (int x = 0; x < GameGrid.STEP_X; x++) {
            for (int y = 0; y < GameGrid.STEP_Y; y++) {
                if (x < 5) {
                    canvas.drawBitmap(getImageCache().getImage(grid.getBackgroundBuyXY(x, y)),
                            stepX(x), stepY(y), null);
                }
                if (grid.getImageBuyXY(x, y) > -1) {
                    canvas.drawBitmap(getImageCache().getImage(grid.getImageBuyXY(x, y)),
                            stepX(x), stepY(y), null);
                }
            }
        }
    }
}
