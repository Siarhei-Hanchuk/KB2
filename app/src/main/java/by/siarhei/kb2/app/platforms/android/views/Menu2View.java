package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.platforms.android.helpers.ImageCache;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.platforms.android.views.RootView;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;
import by.siarhei.kb2.app.ui.menus.GoldChestMenu;

public class Menu2View extends RootView {
    public Menu2View(MainView mainView) {
        super(mainView);
    }

    public void draw(@NonNull Canvas canvas, ServerView serverView) {
        Painter painter = mainView.getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        GoldChestMenu menu = (GoldChestMenu) serverView.getMenu();
        painter.drawText(menu.getTitle(), 0,
                mainView.textHeight(), mainView.getDefaultPaint(), Painter.ALIGN_CENTER);

        ImageCache imageCache = mainView.getImageCache();//ImageCache.getInstance(getResources(), (int)(stepX() * 1.5), (int)(stepY()*1.5));

        Paint paint = new Paint(mainView.getDefaultPaint());
        paint.setTextSize(mainView.textHeight() / 2);

        int i;
        for (i = 0; i < menu.getCount(); i++) {
            painter.drawText(menu.getItemDescription(i), mainView.stepX() * i, mainView.stepY() * 2 - mainView.textHeight() / 1.5f, paint);
            Bitmap image = imageCache.getImage(menu.getItemImageId(i));
            painter.drawBitmap(image, mainView.stepX() * i, mainView.stepY() * 2);
        }
        if(menu.withExit()) {
            String text = i18n.translate("mainMenu_exit");
            painter.drawText(text , mainView.stepX() * (i + 1) - paint.measureText(text), mainView.stepY() * 2 - mainView.textHeight() / 1.5f, paint);
            Bitmap image = imageCache.getImage(R.drawable.status_back);
            painter.drawBitmap(image, mainView.stepX() * i, mainView.stepY() * 2);
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        Painter painter = mainView.getPainter(null);
        double y = event.getY();
        double x = event.getX();
        int item = -1;
        if(x > painter.getXOffset() && y > mainView.stepY() * 2 && y < mainView.stepY() * 3) {
            item = ((int) x - painter.getXOffset()) / mainView.stepX();
        }
        selectRequest(item);

        return true;
    }

    private void selectRequest(int item) {
        Request request = new Request();
        request.setAction(Request.ACTION_SELECT);
        request.setMenuItem(item);
        Server.getServer().request(request);
    }
}
