package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.BattleAskController;
import com.neschur.kb2.app.entities.Castle;
import com.neschur.kb2.app.entities.Fighting;

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
        calcOffsets();
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
        if (fighting instanceof Castle) {
            canvas.drawText(i18n.translate(R.string.battle_begin_castle) + " " +
                            i18n.translate("entity_castle_names_name" + ((Castle) fighting).getNameId()),
                    (int) (getWidth() * 0.05), menuItemHeight(), getDefaultPaint());
        }

        if (playerHasArmy()) {
            canvas.drawText(i18n.translate(R.string.battle_begin_ask),
                    (int) (getWidth() * 0.33), menuItemHeight() * 2, getDefaultPaint());

            canvas.drawText(i18n.translate(R.string.battle_begin_ask_yes),
                    (int) (getWidth() * 0.33), getHeight() - menuItemHeight() * 2, getDefaultPaint());

            canvas.drawText(i18n.translate(R.string.battle_begin_ask_no),
                    (int) (getWidth() * 0.66), getHeight() - menuItemHeight() * 2, getDefaultPaint());
        } else {
            canvas.drawText(i18n.translate(R.string.battle_begin_noArmy),
                    (int) (getWidth() * 0.33), menuItemHeight() * 2, getDefaultPaint());
        }
    }

    private boolean playerHasArmy() {
        return !controller.getGame().getPlayer().noArmy();
    }
}
