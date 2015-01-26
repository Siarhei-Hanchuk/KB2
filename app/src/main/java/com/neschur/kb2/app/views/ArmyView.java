package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.warriors.WarriorSquad;

public class ArmyView extends View {
    public ArmyView(Context context, GameController gameController, ViewClosable closeCallback) {
        super(context, gameController, closeCallback);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        closeCallback.viewClose();
        drawThread.refresh();
        return super.onTouchEvent(event);
    }

    public void draw(Canvas canvas) {
        Paint paint = getDefaultPaint();
        canvas.drawColor(Color.BLACK);

        int imageWidth = (getWidth() / 6);
        int imageHeight = (getHeight() / 5);
        for (int i = 0; i < 5; i++) {
            WarriorSquad squad = player.getWarriorSquad(i);
            if (squad == null)
                continue;
            Bitmap image = Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(
                            getContext().getResources(), squad.getWarrior().getId()
                    ),
                    imageWidth, imageHeight, false
            );

            canvas.drawBitmap(image, 0, imageHeight * i, null);
            canvas.drawText(I18n.translate("army_names_" + squad.getWarrior().getTextId()),
                    imageWidth + 10, menuItemHeight() + imageHeight * i, paint);
            canvas.drawText(Integer.toString(squad.getCount()),
                    imageWidth * 2 + 10, menuItemHeight() + imageHeight * i, paint);
        }

    }
}
