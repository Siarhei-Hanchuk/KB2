package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.BattleAskController;
import com.neschur.kb2.app.entities.Castle;
import com.neschur.kb2.app.entities.Fighting;
import com.neschur.kb2.app.platforms.android.views.helpers.Painter;

public class BattleAskView extends ViewImpl {
    private final BattleAskController controller;
    private final Fighting fighting;

    public BattleAskView(Context context, BattleAskController controller, Fighting fighting) {
        super(context, controller);
        this.controller = controller;
        this.fighting = fighting;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (event.getY() > getHeight() / 2 && playerHasArmy()) {
            if (event.getX() < getWidth() / 2)
                controller.startBattle();
            else
                controller.viewClose();
        }
        if (!playerHasArmy()) {
            controller.viewClose();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);

        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        if (fighting instanceof Castle) {
            painter.drawText(i18n.translate(R.string.battle_begin_castle) + " " +
                            i18n.translate("entity_castle_names_name" + ((Castle) fighting).getNameId()),
                    0, menuItemHeight(), getDefaultPaint());
        }

        if (playerHasArmy()) {
            painter.drawText(i18n.translate(R.string.battle_begin_ask),
                    0, menuItemHeight() * 2, getDefaultPaint(), Painter.ALIGN_CENTER);

            painter.drawText(i18n.translate(R.string.battle_begin_ask_yes),
                    0, getHeight() - menuItemHeight() * 2, getDefaultPaint());

            painter.drawText(i18n.translate(R.string.battle_begin_ask_no),
                    0, getHeight() - menuItemHeight() * 2, getDefaultPaint(), Painter.ALIGN_RIGHT);
        } else {
            painter.drawText(i18n.translate(R.string.battle_begin_noArmy),
                    0, menuItemHeight() * 2, getDefaultPaint(), Painter.ALIGN_CENTER);
        }
    }

    private boolean playerHasArmy() {
        return controller.getGame().getPlayer().hasArmy();
    }
}
