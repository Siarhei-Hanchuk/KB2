package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Canvas;
import android.graphics.Color;
import androidx.annotation.NonNull;

import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Response;
import by.siarhei.kb2.app.server.warriors.WarriorSquad;

public class PlayerArmyView extends RootView {
    public PlayerArmyView(MainView mainView) {
        super(mainView);
    }

    public void draw(@NonNull Canvas canvas, Response response) {
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        for (int i = 0; i < 5; i++) {
            WarriorSquad squad = response.getPlayer().getWarriorSquad(i);
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
            WarriorSquad squad = response.getPlayer().getWarriorSquad(i);
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
