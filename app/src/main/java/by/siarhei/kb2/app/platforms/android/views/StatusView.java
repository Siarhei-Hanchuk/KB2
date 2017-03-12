package by.siarhei.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.controllers.PlayerViewsController;
import by.siarhei.kb2.app.models.Player;
import by.siarhei.kb2.app.platforms.android.views.helpers.Painter;

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
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        drawItem(painter, 1, "authority", player.getAuthority());
        drawItem(painter, 3, "money", player.getMoney());
        drawItem(painter, 4, "salary", player.getSalary());
        drawItem(painter, 5, "armySalary", 0);
        drawItem(painter, 7, "magickPower", player.getMagic().getMagicPower());
        drawItem(painter, 8, "maxMagickCount", player.getMagic().getMagicMaxCount());
        drawItem(painter, 10, "tornadoCount", player.getMagic().getTornado());
        drawItem(painter, 11, "ancientMapCount", 0);
        drawItem(painter, 13, "weeks", viewController.getGame().getWeeks());
    }

    private void drawItem(Painter painter, int n, String attr, int value) {
        painter.drawText(i18n.translate("player_attrs_" + attr) + ": " + value,
                10, textHeight() * n, getDefaultPaint());
    }
}
