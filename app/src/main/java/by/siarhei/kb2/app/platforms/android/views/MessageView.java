package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;

import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Response;

public class MessageView extends RootView {
    public MessageView(MainView mainView) {
        super(mainView);
    }

    public void draw(@NonNull Canvas canvas, Response response) {
        int realWidth = mainView.stepX() * 6;
        Painter painter = mainView.getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        String text = response.getMessage().getText();
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
