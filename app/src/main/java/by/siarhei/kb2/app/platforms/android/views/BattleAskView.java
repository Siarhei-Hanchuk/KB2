package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Canvas;
import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.Response;
import by.siarhei.kb2.app.server.entities.Castle;
import by.siarhei.kb2.app.server.entities.Fighting;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;

public class BattleAskView extends RootView {
    public BattleAskView(MainView mainView) {
        super(mainView);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        Request request = new Request();
        if (event.getY() > getHeight() / 2) {
            if (event.getX() < getWidth() / 2)
                request.setAction(Request.ACTION_ACCEPT_BATTLE);
            else
                request.setAction(Request.ACTION_DECLINE_BATTLE);
        }
        return Server.getServer().request(request);
    }

    @Override
    public void draw(@NonNull Canvas canvas, Response response) {
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);
        Fighting fighting = response.getFighting();

        if (fighting instanceof Castle) {
            painter.drawText(i18n.translate(R.string.battle_begin_castle) + " " +
                            i18n.translate("entity_castle_names_name" + ((Castle) fighting).getNameId()),
                    0, menuItemHeight(), getDefaultPaint());
        }

        if (isCaptainsActivated(response)) {
            painter.drawText(i18n.translate(R.string.battle_begin_ask),
                    0, menuItemHeight() * 2, getDefaultPaint(), Painter.ALIGN_CENTER);

            painter.drawText(i18n.translate(getCaptainPowerString(response)),
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

    private boolean isCaptainsActivated(Response response) {
//        return controller.getGame().getPlayer().isCaptainsActivated();
        return true;
    }

    private int getCaptainPowerString(Response response) {
        int ratio = response.getAuthority() / response.getFighting().getAuthority();
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
