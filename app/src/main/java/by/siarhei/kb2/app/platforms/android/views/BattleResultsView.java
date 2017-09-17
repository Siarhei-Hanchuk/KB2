package by.siarhei.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import java.util.HashMap;

import by.siarhei.kb2.app.controllers.ViewController;
import by.siarhei.kb2.app.platforms.android.views.helpers.Painter;
import by.siarhei.kb2.app.ui.messages.Message;
import by.siarhei.kb2.app.warriors.Warrior;
import by.siarhei.kb2.app.warriors.WarriorFactory;


public class BattleResultsView  extends ViewImpl  {
    private final Message message;
    private HashMap<String, Integer> casualties;

    public BattleResultsView(Context context, ViewController viewController, Message message,
                             HashMap<String, Integer> casualties) {
        super(context, viewController);
        this.message = message;
        this.casualties = casualties;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        closeMessage();
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        int realWidth = stepX() * 6;
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        String text = message.getText();
        float textLength = getDefaultPaint().measureText(text);
        if (textLength < realWidth ) {
            painter.drawText(text, 10, 100, getDefaultPaint());
        } else {
            int count = (int) (text.length() / (textLength / realWidth ));
            int length = (text.length() + count - 1) / count;
            for (int ix = 0, pos = 0; ix < length; ix++, pos += count) {
                painter.drawText(text.substring(pos, Math.min(text.length(), pos + count)).trim()
                        , 10, 100 * (ix + 1), getDefaultPaint());
            }
        }
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setTextSize(textHeight());
        text = i18n.translate("battle_finish_casualties");
        textLength = getDefaultPaint().measureText(text);
        painter.drawText(text, (realWidth-textLength)/2, 400, redPaint);
        int i = 0;
        for(String id: casualties.keySet()) {
            Warrior warrior = WarriorFactory.create(id);
            int newX = (i % 2)*3;
            int newY = i / 2 +2;
            painter.drawBitmap(getImageCache().getImage(warrior.getId())
                    , newX*stepX(), stepY() * newY);
            painter.drawText(i18n.translate("army_names_" + id),
                    (newX+1)*stepX() + 10, stepY() * newY + textHeight(), getDefaultPaint());
            painter.drawText(Integer.toString(casualties.get(id)),
                    (newX+1)*stepX() + 10, stepY() * newY + stepY(), getDefaultPaint());
            i++;
        }
    }

    private void closeMessage() {
        message.action();
        viewController.viewClose();
    }
}
