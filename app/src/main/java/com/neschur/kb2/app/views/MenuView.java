package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.ui.menus.Menu;

/**
 * Created by siarhei on 11.1.15.
 */
public class MenuView extends View {
    private Menu menu;

    public MenuView(Context context, Menu menu, GameController gameController, ViewClosable closeCallback) {
        super(context, gameController, closeCallback);
        this.menu = menu;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        double y = event.getY();
        int item = (int) y / ITEM_SIZE;
        select(item);
        drawThread.refresh();
        return super.onTouchEvent(event);
    }

    public void select(int item) {
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

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        int i;
        for (i = 0; i < menu.getCount(); i++) {
            canvas.drawText(menu.getItemDescription(i), 10,
                    MenuView.ITEM_SIZE + MenuView.ITEM_SIZE * i, defaultPaint);
        }
        if (menu.withExit())
            canvas.drawText("Exit", 10,
                    MenuView.ITEM_SIZE + MenuView.ITEM_SIZE * i, defaultPaint);
        if (menu.withMoney())
            canvas.drawText("Money: " + gameController.getPlayer().getMoney(), 700,
                    MenuView.ITEM_SIZE, defaultPaint);
    }

}
