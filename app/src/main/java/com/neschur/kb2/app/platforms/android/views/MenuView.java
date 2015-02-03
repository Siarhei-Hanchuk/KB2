package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.controllers.ViewController;
import com.neschur.kb2.app.ui.menus.Menu;

class MenuView extends ViewImpl {
    private final Menu menu;

    public MenuView(Context context, ViewController viewController, Menu menu) {
        super(context, viewController);
        this.menu = menu;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        double y = event.getY();
        int item = (int) y / menuItemHeight();
        select(item);
        return super.onTouchEvent(event);
    }

    @Override
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
            canvas.drawText("Money: " + viewController.getGame().getPlayer().getMoney(),
                    (int) (getWidth() * 0.5),
                    menuItemHeight() + menuItemHeight() * i, paint);
    }

    private void select(int item) {
        boolean result = false;
        if (item < menu.getCount()) {
            result = menu.select(item);
            refresh();
        }
        if (menu.withExit()) {
            if (item == menu.getCount() || result)
                if (menu.getMenuMode() > 0) {
                    menu.resetMenuMode();
                    refresh();
                } else {
                    viewController.viewClose();
                }
        } else {
            if (result)
                viewController.viewClose();
        }
    }

}
