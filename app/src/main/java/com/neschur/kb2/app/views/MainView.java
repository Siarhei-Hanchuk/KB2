package com.neschur.kb2.app.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.ImageCache;
import com.neschur.kb2.app.models.GameGrid;
import com.neschur.kb2.app.views.interfaces.ViewController;

public class MainView extends View {
    private ViewController viewController;

    public MainView(ViewController viewController) {
        super(viewController.getContext(), null);
        this.viewController = viewController;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        int x = (int) event.getX() - xOffset;
        int y = (int) event.getY() - yOffset;
        if (x > this.stepX() * 5) {
            int item = (y / stepY());// + 5 * (x/stepX() - 5);
            viewController.touchMenu(item);
        } else {
            int height_2_5 = stepY() * 2;
            int height_3_5 = stepY() * 3;
            int width_2_5 = stepX() * 2;
            int width_3_5 = stepX() * 3;
            if (y > height_3_5) {
                if (x > width_2_5 && x < width_3_5) {
                    viewController.touchDown();
                }
                if (x < width_2_5) {
                    viewController.touchDownLeft();
                }
                if (x > width_3_5) {
                    viewController.touchDownRight();
                }
            }
            if (y < height_2_5) {
                if (x > width_2_5 && x < width_3_5) {
                    viewController.touchUp();
                }
                if (x < width_2_5) {
                    viewController.touchUpLeft();
                }
                if (x > width_3_5) {
                    viewController.touchUpRight();
                }
            }
            if (x > width_3_5 && y > height_2_5 && y < height_3_5) {
                viewController.touchRight();
            }
            if (x < width_2_5 && y > height_2_5 && y < height_3_5) {
                viewController.touchLeft();
            }
        }
        drawThread.refresh();
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        GameGrid grid = viewController.getGameGrid();
        canvas.drawColor(Color.BLACK);
        ImageCache imageCache = ImageCache.getInstance(getResources(), stepX(), stepY());
        calcOffsets();

        for (int x = 0; x < GameGrid.STEP_X; x++) {
            for (int y = 0; y < GameGrid.STEP_Y; y++) {
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
