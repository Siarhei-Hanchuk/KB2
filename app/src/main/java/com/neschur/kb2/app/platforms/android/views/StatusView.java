package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.controllers.PlayerViewsController;
import com.neschur.kb2.app.models.Player;

public class StatusView extends ViewImpl {
    private final Player player;

    public StatusView(Context context, PlayerViewsController playerViewsController) {
        super(context, playerViewsController);
        this.player = playerViewsController.getPlayer();
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        viewController.viewClose();
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);

        drawItem(canvas, 1, "authority", player.getAuthority());
        drawItem(canvas, 3, "money", player.getMoney());
        drawItem(canvas, 4, "salary", player.getSalary());
        drawItem(canvas, 5, "armySalary", 0);
        drawItem(canvas, 7, "magickPower", player.getMagic().getMagicPower());
        drawItem(canvas, 8, "maxMagickCount", player.getMagic().getMagicMaxCount());
        drawItem(canvas, 10, "tornadoCount", player.getMagic().getTornado());
        drawItem(canvas, 11, "ancientMapCount", 0);
        drawItem(canvas, 13, "weeks", viewController.getGame().getWeeks());
    }

    private void drawItem(Canvas canvas, int n, String attr, int value) {
        canvas.drawText(i18n.translate("player_attrs_" + attr) + ": " + value,
                10, textHeight() * n, getDefaultPaint());
    }
}
