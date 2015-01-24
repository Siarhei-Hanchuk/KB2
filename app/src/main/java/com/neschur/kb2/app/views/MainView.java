package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.MainController;
import com.neschur.kb2.app.ui.ImageCache;

public class MainView extends View {
    private MainController mainController;

    public MainView(Context context, MainController mainController) {
        super(context, null, null);
        this.mainController = mainController;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getX() > this.stepX() * 5) {
            int item = (int) event.getY() / stepY();
            mainController.touchMenu(item);
        } else {
            int height_2_5 = stepY() * 2;
            int height_3_5 = stepY() * 3;
            int width_2_5 = stepX() * 2;
            int width_3_5 = stepX() * 3;
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

    @Override
    public void draw(Canvas canvas) {
        imageCache = ImageCache.getInstance(getResources(), stepX(), stepY());;
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                if (x < 5) {
                    Bitmap background = imageCache.getImage(
                            mainController.getGameGrid().getBackgroundBuyXY(x, y));
                    if (background == null) {
                        background = imageCache.getImage(R.drawable.emo);
                    }
                    canvas.drawBitmap(background, x * stepX(), y * stepY(), null);
                }
                Bitmap image = imageCache.getImage(
                        mainController.getGameGrid().getImageBuyXY(x, y));

                if (image == null) {
                    image = imageCache.getImage(R.drawable.emo);
                }
                canvas.drawBitmap(image, x * stepX(), y * stepY(), null);
            }
        }
    }
}
