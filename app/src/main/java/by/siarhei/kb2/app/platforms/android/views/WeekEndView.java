package by.siarhei.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.controllers.ViewController;
import by.siarhei.kb2.app.entities.City;
import by.siarhei.kb2.app.models.Player;
import by.siarhei.kb2.app.platforms.android.views.helpers.Painter;

public class WeekEndView extends ViewImpl {
    private final Player player;
    private final String armyTextId;
    private final City city;

    public WeekEndView(Context context, ViewController controller, String armyTextId, City city) {
        super(context, controller);
        this.armyTextId = armyTextId;
        this.city = city;
        this.player = controller.getGame().getPlayer();
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        viewController.viewClose();
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        String text = i18n.translate(R.string.weekEnd_messages_title);

        int delta = ((int) getDefaultPaint().measureText(text) + 1 - getWidth()) / 2;
        delta = (delta > 0) ? delta : 0;
        canvas.drawText(text, delta, textHeight(), getDefaultPaint());

        drawItem(painter, 3, "money", player.getMoney());
        drawItem(painter, 4, "salary", player.getSalary());
        drawItem(painter, 5, "armySalary", 0);

        drawItem(painter, 7, "armyRefresh", i18n.translate("army_names_" + armyTextId));
        drawItem(painter, 8, "armyRefresh2", null);
        drawItem(painter, 10, "armyRefresh3", null);
        drawItem(painter, 12, "cityRefresh", i18n.translate("entity_city_names_name" + city.getNameId()));
    }

    private void drawItem(Painter painter, int n, String attr, Object value) {
        String text;
        if (value != null) {
            if (value instanceof Integer) {
                text = i18n.translate("weekEnd_messages_" + attr) + ": " + value;
            } else {
                text = i18n.translate("weekEnd_messages_" + attr, (String) value);
            }
        } else {
            text = i18n.translate("weekEnd_messages_" + attr);
        }
        float textLength = getDefaultPaint().measureText(text);
        if (textLength < getWidth()) {
            painter.drawText(text, 10, textHeight() * (n), getDefaultPaint());
        } else {
            int count = (int) (text.length() / (textLength / getWidth()));
            int length = (text.length() + count - 1) / count;
            for (int ix = 0, pos = 0; ix < length; ix++, pos += count) {
                painter.drawText(text.substring(pos, Math.min(text.length(), pos + count)).trim()
                        , 10, (textHeight() * n) + textHeight() * ix, getDefaultPaint());
            }
        }
    }
}

