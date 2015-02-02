package com.neschur.kb2.app.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.controllers.PlayerViewsController;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.warriors.WarriorSquad;

class ArmyView extends View {
    private final Player player;

    public ArmyView(PlayerViewsController playerViewsController) {
        super(playerViewsController);
        this.player = playerViewsController.getPlayer();
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        viewController.viewClose();
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Paint paint = getDefaultPaint();
        canvas.drawColor(Color.BLACK);

        int imageWidth = stepX();
        int imageHeight = stepY();
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
