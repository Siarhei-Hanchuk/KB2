package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Response;


public class BattleResultsView extends RootView  {
    //  private HashMap<String, Integer> casualties;

    public BattleResultsView(MainView mainView) {
        super(mainView);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
//        closeMessage();
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas, Response response) {
        int realWidth = stepX() * 6;
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        String text = response.getMessage().getText();
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

        // TODO - check
//        HashMap<String, Integer> casualties;
//        for(String id: casualties.keySet()) {
//            Warrior warrior = WarriorFactory.create(id);
//            int newX = (i % 2)*3;
//            int newY = i / 2 +2;
//            painter.drawBitmap(getImageCache().getImage(warrior.getId())
//                    , newX*stepX(), stepY() * newY);
//            painter.drawText(i18n.translate("army_names_" + id),
//                    (newX+1)*stepX() + 10, stepY() * newY + textHeight(), getDefaultPaint());
//            painter.drawText(Integer.toString(casualties.get(id)),
//                    (newX+1)*stepX() + 10, stepY() * newY + stepY(), getDefaultPaint());
//            i++;
//        }
    }
}
