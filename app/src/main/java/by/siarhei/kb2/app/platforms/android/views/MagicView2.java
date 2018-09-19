package by.siarhei.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import java.util.HashMap;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.controllers.MagicViewController;
import by.siarhei.kb2.app.platforms.android.XMainView;
import by.siarhei.kb2.app.platforms.android.drawers.Drawer;
import by.siarhei.kb2.app.platforms.android.helpers.ImageCache;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.GameGrid;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.ServerView;
import by.siarhei.kb2.app.server.models.Magic;
import by.siarhei.kb2.app.server.warriors.WarriorFactory;

class MagicView2 extends RootView {
    private final Magic magic = new Magic(1, 2);
    private final HashMap<Integer, String> armyIdCache = new HashMap<>();
    private int mode = 0;

    public MagicView2(Canvas canvas, XMainView mainView) {
        super(canvas, mainView);
    }

    @Override
    public void onTouchEvent(@NonNull MotionEvent event) {
//        if (mode == 0) {
//            int item = (int) event.getY() / menuItemHeight();
//            if (item == 4) {
//                mode = 1;
//                refresh();
//            } else {
//                viewController.viewClose();
//            }
//        } else if (mode == 1) {
//            int x = (int) event.getX() / stepX() + 1;
//            int y = (int) event.getY() / stepY() + 1;
//            magicViewController.takeArmy(armyIdCache.get(x * y));
//            viewController.viewClose();
//        }
//        return super.onTouchEvent(event);
    }


    @Override
    public void draw(ServerView serverView) {
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        if (mode != 1) {
            for (int i = 1; i < 8; i++) {
                canvas.drawText(i18n.translate("magic_hiking_magic" + i) + ": "
                                + magic.getCampingMagic(i - 1),
                        0, i * menuItemHeight(), getDefaultPaint());
            }
        } else {
            ImageCache imageCache = ImageCache.getInstance(getResources(), stepX(), stepY());
            int count = 0;
            for (String army : WarriorFactory.getAllArmyTextIds()) {
                int imageId = 0;
                try {
                    imageId = R.drawable.class.getField("army_" + army).getInt(new R.string());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                painter.drawBitmap(imageCache.getImage(imageId), (count % GameGrid.STEP_Y) * stepX(),
                        (count / GameGrid.STEP_Y) * stepY());
                count++;
                armyIdCache.put(count, army);
            }
        }
    }
}
