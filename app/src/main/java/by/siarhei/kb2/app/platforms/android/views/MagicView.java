package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Canvas;
import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.MotionEvent;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.server.GameGrid;
import by.siarhei.kb2.app.server.Request;
import by.siarhei.kb2.app.server.Server;
import by.siarhei.kb2.app.server.Response;
import by.siarhei.kb2.app.server.models.Magic;
import by.siarhei.kb2.app.platforms.android.helpers.ImageCache;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;
import by.siarhei.kb2.app.server.warriors.WarriorFactory;

import java.lang.reflect.Field;
import java.util.HashMap;

class MagicView extends RootView {
    private Magic magic;
    private int mode = 0;
    private final HashMap<Integer, String> armyIdCache = new HashMap<>();

    public MagicView(MainView mainView) {
        super(mainView);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (mode == 0) {
            int item = (int) event.getY() / menuItemHeight();
            if (item == 4) {
                mode = 1;
                return false;
            }
        } else if (mode == 1) {
            int x = (int) event.getX() / stepX();
            int y = (int) event.getY() / stepY();
            Request request = new Request();
            request.setAction(Request.ACTION_GIVE_ARMY);
            request.setData(armyIdCache.get(y * 5 + x));
            Server.getServer().request(request);
        }
        return true;
    }

    @Override
    public void draw(@NonNull Canvas canvas, Response response) {
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        Magic magic = response.getPlayer().getMagic();

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
                    Field idField = R.drawable.class.getField("army_" + army);
                    imageId = idField.getInt(idField);
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
