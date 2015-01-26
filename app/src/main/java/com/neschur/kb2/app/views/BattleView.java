package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Canvas;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.BattleController;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.ui.ImageCache;

public class BattleView extends View {
    private BattleController battleController;

    public BattleView(Context context, GameController gameController,
                      BattleController battleController, ViewClosable closeCallback) {
        super(context, gameController, closeCallback);
        this.battleController = battleController;
    }

    public void draw(Canvas canvas) {
        ImageCache imageCache = ImageCache.getInstance(getResources(), stepX(), stepY());
        int field[][] = battleController.getIdsField();
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 5; y++) {
                canvas.drawBitmap(imageCache.getImage(R.drawable.land),
                        x * stepX(), y * stepY(), null);
                canvas.drawBitmap(imageCache.getImage(field[x][y]),
                        x * stepX(), y * stepY(), null);
            }
        }
    }
}
