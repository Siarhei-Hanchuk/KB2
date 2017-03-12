package by.siarhei.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.controllers.PlayerViewsController;
import by.siarhei.kb2.app.models.Player;
import by.siarhei.kb2.app.platforms.android.views.helpers.Painter;
import by.siarhei.kb2.app.warriors.WarriorSquad;

class ArmyView extends ViewImpl {
    private final Player player;

    public ArmyView(Context context, PlayerViewsController playerViewsController) {
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

        for (int i = 0; i < 5; i++) {
            WarriorSquad squad = player.getWarriorSquad(i);
            if (squad == null)
                continue;

            painter.drawBitmap(getImageCache().getImage(squad.getWarrior().getId())
                    , 0, stepY() * i);
            painter.drawText(i18n.translate("army_names_" + squad.getWarrior().getTextId()),
                    stepX() + 10, stepY() * i + textHeight(), getDefaultPaint());
            painter.drawText(Integer.toString(squad.getCount()),
                    stepX() + 10, stepY() * i + stepY(), getDefaultPaint());
        }

    }
}
