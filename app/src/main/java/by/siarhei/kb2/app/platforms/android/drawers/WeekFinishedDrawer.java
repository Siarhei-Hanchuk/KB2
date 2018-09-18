package by.siarhei.kb2.app.platforms.android.drawers;

import android.graphics.Canvas;
import android.graphics.Color;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.platforms.android.XMainView;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;

public class WeekFinishedDrawer extends Drawer {
    public WeekFinishedDrawer(Canvas canvas, XMainView mainView) {
        super(canvas, mainView);
    }

    public void draw(ServerView serverView) {
        Painter painter = mainView.getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        String text = Server.getI18n().translate(R.string.weekEnd_messages_title);

        int delta = ((int) mainView.getDefaultPaint().measureText(text) + 1 - mainView.getWidth()) / 2;
        delta = (delta > 0) ? delta : 0;
        canvas.drawText(text, delta, mainView.textHeight(), mainView.getDefaultPaint());

        drawItem(mainView, painter, 3, "money", serverView.getMoney());
        drawItem(mainView, painter, 4, "salary", serverView.getSalary());
        drawItem(mainView, painter, 5, "armySalary", 0);

        drawItem(mainView, painter, 7, "armyRefresh", Server.getI18n().translate("army_names_" + serverView.getUpdatedWarrior().getTextId()));
        drawItem(mainView, painter, 8, "armyRefresh2", null);
        drawItem(mainView, painter, 10, "armyRefresh3", null);
        drawItem(mainView, painter, 12, "cityRefresh", Server.getI18n().translate("entity_city_names_name" + serverView.getUpdatedCity().getNameId()));
    }

    private static void drawItem(XMainView mainView, Painter painter, int n, String attr, Object value) {
        String text;
        if (value != null) {
            if (value instanceof Integer) {
                text = Server.getI18n().translate("weekEnd_messages_" + attr) + ": " + value;
            } else {
                text = Server.getI18n().translate("weekEnd_messages_" + attr, (String) value);
            }
        } else {
            text = Server.getI18n().translate("weekEnd_messages_" + attr);
        }
        float textLength = mainView.getDefaultPaint().measureText(text);
        if (textLength < mainView.getWidth()) {
            painter.drawText(text, 10, mainView.textHeight() * (n), mainView.getDefaultPaint());
        } else {
            int count = (int) (text.length() / (textLength / mainView.getWidth()));
            int length = (text.length() + count - 1) / count;
            for (int ix = 0, pos = 0; ix < length; ix++, pos += count) {
                painter.drawText(text.substring(pos, Math.min(text.length(), pos + count)).trim()
                        , 10, (mainView.textHeight() * n) + mainView.textHeight() * ix, mainView.getDefaultPaint());
            }
        }
    }
}
