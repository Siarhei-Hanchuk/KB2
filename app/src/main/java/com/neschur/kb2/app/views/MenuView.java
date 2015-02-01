package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.ui.menus.Menu;
import com.neschur.kb2.app.views.interfaces.ViewClosable;

public class MenuView extends View {
    private Menu menu;

    public MenuView(Context context, Menu menu, ViewClosable closeCallback) {
        super(context, closeCallback);
        this.menu = menu;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        double y = event.getY();
        int item = (int) y / menuItemHeight();
        select(item);
        drawThread.refresh();
        return super.onTouchEvent(event);
    }

    public void draw(@NonNull Canvas canvas) {
        Paint paint = getDefaultPaint();
        canvas.drawColor(Color.BLACK);

        int i;
        for (i = 0; i < menu.getCount(); i++) {
            canvas.drawText(menu.getItemDescription(i), 10,
                    menuItemHeight() + menuItemHeight() * i, paint);
        }
        if (menu.withExit())
            canvas.drawText("Exit", 10,
                    menuItemHeight() + menuItemHeight() * i, paint);
        if (menu.withMoney())
            canvas.drawText("Money: " + 0,//gameController.getPlayer().getMoney(),
                    (int) (getWidth() * 0.5),
                    menuItemHeight() + menuItemHeight() * i, paint);
    }

    private void select(int item) {
        boolean result = false;
        if (item < menu.getCount())
            result = menu.select(item);
        if (menu.withExit()) {
            if (item == menu.getCount() || result)
                if (menu.getMenuMode() > 0) {
                    menu.resetMenuMode();
                } else {
                    closeCallback.viewClose();
                }
        } else {
            if (result)
                closeCallback.viewClose();
        }
    }

}
