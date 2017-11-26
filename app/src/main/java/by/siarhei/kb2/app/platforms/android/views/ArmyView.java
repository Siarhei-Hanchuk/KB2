package by.siarhei.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import by.siarhei.kb2.app.controllers.PlayerViewsController;
import by.siarhei.kb2.app.models.Player;
import by.siarhei.kb2.app.platforms.android.views.helpers.Painter;
import by.siarhei.kb2.app.warriors.WarriorSquad;

class ArmyView extends ViewImpl {
    private final Player player;
    private PlayerViewsController playerViewsController;

    public ArmyView(Context context, PlayerViewsController playerViewsController) {
        super(context, playerViewsController);
        this.player = playerViewsController.getPlayer();
        this.playerViewsController = playerViewsController;

        final GestureDetector gd = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                viewController.viewClose();
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                ArmyView.this.playerViewsController.setContentView(
                        playerViewsController.getViewFactory().getArmyFireView(playerViewsController));
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
        });

        this.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gd.onTouchEvent(event);
            }
        });
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
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

        for (int i = 5; i < 10; i++) {
            WarriorSquad squad = player.getWarriorSquad(i);
            if (squad == null)
                continue;

            painter.drawBitmap(getImageCache().getImage(squad.getWarrior().getId())
                    , stepX() * 3, stepY() * (i - 5));
            painter.drawText(i18n.translate("army_names_" + squad.getWarrior().getTextId()),
                    stepX() * 4 + 10, stepY() * (i - 5) + textHeight(), getDefaultPaint());
            painter.drawText(Integer.toString(squad.getCount()),
                    stepX() * 4 + 10, stepY() * (i - 5) + stepY(), getDefaultPaint());
        }
    }
}
