package by.siarhei.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.controllers.BattleAskController;
import by.siarhei.kb2.app.entities.Castle;
import by.siarhei.kb2.app.entities.Fighting;
import by.siarhei.kb2.app.platforms.android.views.helpers.Painter;

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
        boolean captainsWasActivated = isCaptainsActivated();
        if (event.getY() > getHeight() / 2 && captainsWasActivated) {
            if (event.getX() < getWidth() / 2)
                controller.startBattle();
            else
                controller.viewClose();
        }
        if (!captainsWasActivated) {
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

        if (isCaptainsActivated()) {
            painter.drawText(i18n.translate(R.string.battle_begin_ask),
                    0, menuItemHeight() * 2, getDefaultPaint(), Painter.ALIGN_CENTER);

            painter.drawText(i18n.translate(getCaptainPowerString()),
                    0, menuItemHeight() * 3, getDefaultPaint(), Painter.ALIGN_CENTER);

            painter.drawText(i18n.translate(R.string.battle_begin_ask_yes),
                    0, getHeight() - menuItemHeight() * 2, getDefaultPaint());

            painter.drawText(i18n.translate(R.string.battle_begin_ask_no),
                    0, getHeight() - menuItemHeight() * 2, getDefaultPaint(), Painter.ALIGN_RIGHT);
        } else {
            painter.drawText(i18n.translate(R.string.battle_begin_noArmy),
                    0, menuItemHeight() * 2, getDefaultPaint(), Painter.ALIGN_CENTER);
        }
    }

    private boolean isCaptainsActivated() {
        return controller.getGame().getPlayer().isCaptainsActivated();
    }

    private int getCaptainPowerString() {
        int ratio = controller.getGame().getPlayer().getAuthority() / fighting.getAuthority();
        if (ratio < 0.2) {
            return R.string.battle_begin_ask_level1;
        } else if(ratio < 0.8) {
            return R.string.battle_begin_ask_level2;
        } else if(ratio < 1.2) {
            return R.string.battle_begin_ask_level3;
        } else if(ratio < 1.8) {
            return R.string.battle_begin_ask_level4;
        } else {
            return R.string.battle_begin_ask_level5;
        }
    }
}
