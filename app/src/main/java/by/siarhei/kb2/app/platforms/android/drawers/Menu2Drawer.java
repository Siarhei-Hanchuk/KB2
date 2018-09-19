package by.siarhei.kb2.app.platforms.android.drawers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.platforms.android.XMainView;
import by.siarhei.kb2.app.platforms.android.helpers.ImageCache;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;
import by.siarhei.kb2.app.ui.menus.GoldChestMenu;
import by.siarhei.kb2.app.ui.menus.Menu;

import static by.siarhei.kb2.app.controllers.ApplicationController.i18n;

public class Menu2Drawer extends Drawer {
    public Menu2Drawer(Canvas canvas, XMainView mainView) {
        super(canvas, mainView);
    }

    public void draw(ServerView serverView) {
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
}
