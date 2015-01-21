package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.models.BattleField;
import com.neschur.kb2.app.models.MapPoint;

/**
 * Created by siarhei on 20.1.15.
 */
public class BattleView extends View {
    private BattleController battleController;

    public BattleView(Context context, GameController gameController,
                      BattleController battleController, ViewClosable closeCallback) {
        super(context, gameController, closeCallback);
        this.battleController = battleController;
    }

    public void draw(Canvas canvas) {
        int field[][] = battleController.getIdsField();
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                Bitmap image = oneImage(field[x][y]);
                canvas.drawBitmap(image, x * stepX(), y * stepY(), null);
            }
        }
    }
}
