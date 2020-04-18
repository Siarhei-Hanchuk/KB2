package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Canvas;
import android.graphics.Color;
import androidx.annotation.NonNull;

import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.Response;
import by.siarhei.kb2.app.server.models.Player;

public class StatusView extends RootView {
    public StatusView(MainView mainView) {
        super(mainView);
    }

    public void draw(@NonNull Canvas canvas, Response response) {
        Painter painter = mainView.getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        Player player = response.getPlayer();

        drawItem(painter, 1, "authority", player.getAuthority());
        drawItem(painter, 3, "money", player.getMoney());
        drawItem(painter, 4, "salary", player.getSalary());
        drawItem(painter, 5, "armySalary", 0);
        drawItem(painter, 7, "magicPower", player.getMagic().getMagicPower());
        drawItem(painter, 8, "maxMagicCount", player.getMagic().getMagicMaxCount());
        drawItem(painter, 10, "tornadoCount", player.getMagic().getTornado());
        drawItem(painter, 11, "ancientMapCount", 0);
        drawItem(painter, 13, "weeks", response.getLeftWeeks());
    }

    private void drawItem(Painter painter, int n, String attr, int value) {
        painter.drawText(Server.getI18n().translate("player_attrs_" + attr) + ": " + value,
                10, mainView.textHeight() * n, mainView.getDefaultPaint());
    }
}
