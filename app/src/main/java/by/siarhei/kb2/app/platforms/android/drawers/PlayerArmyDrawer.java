package by.siarhei.kb2.app.platforms.android.drawers;

import android.graphics.Canvas;
import android.graphics.Color;

import by.siarhei.kb2.app.platforms.android.XMainView;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;
import by.siarhei.kb2.app.server.models.Player;
import by.siarhei.kb2.app.server.warriors.WarriorSquad;

public class PlayerArmyDrawer extends Drawer {
    public PlayerArmyDrawer(Canvas canvas, XMainView mainView) {
        super(canvas, mainView);
    }

    public void draw(ServerView serverView) {
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        for (int i = 0; i < 5; i++) {
            WarriorSquad squad = serverView.getPlayer().getWarriorSquad(i);
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
            WarriorSquad squad = serverView.getPlayer().getWarriorSquad(i);
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
