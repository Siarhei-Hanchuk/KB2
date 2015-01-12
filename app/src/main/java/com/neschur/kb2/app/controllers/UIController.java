package com.neschur.kb2.app.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.neschur.kb2.app.MainActivity;

/**
 * Created by siarhei on 8.6.14.
 */
public class UIController {
    private MainActivity activity;
    private MainController mainController;

    public UIController(MainActivity activity, MainController mainController) {
        this.activity = activity;
        this.mainController = mainController;
    }

    public void paint(Canvas canvas) {
        paintField(canvas, mainController.getGameGrid());
    }


    public void paintField(Canvas canvas, GameGrid gameGrid){
        int stepX = canvas.getWidth()/6;
        int stepY = canvas.getHeight()/5;
        for(int x=0;x<6; x++) {
            for (int y = 0; y < 5; y++) {
                Bitmap image = Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(
                                activity.getResources(), gameGrid.getBuyXY(x, y)
                        ),
                        stepX, stepY, false
                );
                if (image != null) {
                    canvas.drawBitmap(image, x * stepX, y * stepY, null);
                }
            }
        }

    }

}
