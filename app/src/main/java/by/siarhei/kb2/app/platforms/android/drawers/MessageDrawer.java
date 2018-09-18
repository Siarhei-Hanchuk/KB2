package by.siarhei.kb2.app.platforms.android.drawers;

import android.graphics.Canvas;
import android.graphics.Color;

import by.siarhei.kb2.app.platforms.android.XMainView;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.ServerView;

public class MessageDrawer extends Drawer {
    public MessageDrawer(Canvas canvas, XMainView mainView) {
        super(canvas, mainView);
    }

    public void draw(ServerView serverView) {
        int realWidth = mainView.stepX() * 6;
        Painter painter = mainView.getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        String text = serverView.getMessage().getText();
        float textLength = mainView.getDefaultPaint().measureText(text);
        if (textLength < realWidth) {
            painter.drawText(text, 10, 100, mainView.getDefaultPaint());
        } else {
            int count = (int) (text.length() / (textLength / realWidth));
            int length = (text.length() + count - 1) / count;
            for (int ix = 0, pos = 0; ix < length; ix++, pos += count) {
                painter.drawText(text.substring(pos, Math.min(text.length(), pos + count)).trim()
                        , 10, 100 * (ix + 1), mainView.getDefaultPaint());
            }
        }
    }
}
