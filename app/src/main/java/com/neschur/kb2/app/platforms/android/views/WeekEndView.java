package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.models.Player;

public class WeekEndView extends ViewImpl {
    private final Player player;
    private String armyTextId;
    private City city;

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
        String text = i18n.translate(R.string.weekEnd_messages_title);

        int delta = ((int) getDefaultPaint().measureText(text) + 1 - getWidth()) / 2;
        delta = (delta > 0) ? delta : 0;
        canvas.drawText(text, delta, textHeight(), getDefaultPaint());

        drawItem(canvas, 3, "money", player.getMoney());
        drawItem(canvas, 4, "salary", player.getSalary());
        drawItem(canvas, 5, "armySalary", 0);

        drawItem(canvas, 7, "armyRefresh", i18n.translate("army_names_" + armyTextId));
        drawItem(canvas, 8, "armyRefresh2", null);
        drawItem(canvas, 10, "armyRefresh3", null);
        drawItem(canvas, 12, "cityRefresh", i18n.translate("entity.city_names_name" + city.getNameId()));
    }

    private void drawItem(Canvas canvas, int n, String attr, Object value) {
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
            canvas.drawText(text, 10, textHeight() * (n), getDefaultPaint());
        } else {
            int count = (int) (text.length() / (textLength / getWidth()));
            int length = (text.length() + count - 1) / count;
            for (int ix = 0, pos = 0; ix < length; ix++, pos += count) {
                canvas.drawText(text.substring(pos, Math.min(text.length(), pos + count)).trim()
                        , 10, (textHeight() * n) + textHeight() * ix, getDefaultPaint());
            }
        }
    }
}

