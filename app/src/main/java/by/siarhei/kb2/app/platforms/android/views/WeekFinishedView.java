package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Canvas;
import android.graphics.Color;
import androidx.annotation.NonNull;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.Response;

public class WeekFinishedView extends RootView {
    public WeekFinishedView(MainView mainView) {
        super(mainView);
    }

    public void draw(@NonNull Canvas canvas, Response response) {
        Painter painter = mainView.getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        String text = Server.getI18n().translate(R.string.weekEnd_messages_title);

        int delta = ((int) mainView.getDefaultPaint().measureText(text) + 1 - mainView.getWidth()) / 2;
        delta = (delta > 0) ? delta : 0;
        canvas.drawText(text, delta, mainView.textHeight(), mainView.getDefaultPaint());

        drawItem(mainView, painter, 3, "money", response.getMoney());
        drawItem(mainView, painter, 4, "salary", response.getSalary());
        drawItem(mainView, painter, 5, "armySalary", 0);

        drawItem(mainView, painter, 7, "armyRefresh", Server.getI18n().translate("army_names_" + response.getUpdatedWarrior().getTextId()));
        drawItem(mainView, painter, 8, "armyRefresh2", null);
        drawItem(mainView, painter, 10, "armyRefresh3", null);
        drawItem(mainView, painter, 12, "cityRefresh", Server.getI18n().translate("entity_city_names_name" + response.getUpdatedCity().getNameId()));
    }

    private static void drawItem(MainView mainView, Painter painter, int n, String attr, Object value) {
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
