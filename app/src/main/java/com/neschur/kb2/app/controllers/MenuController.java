package com.neschur.kb2.app.controllers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.ui.Menu;
import com.neschur.kb2.app.views.Drawable;
import com.neschur.kb2.app.views.MenuView;

/**
 * Created by siarhei on 11.1.15.
 */
public class MenuController implements Drawable {
    private MainActivity activity;
    private MainController mainController;
    private MenuView view;
    private Menu menu;
    private Paint paint;

    public MenuController(MainActivity activity, MainController mainController) {
        this.activity = activity;
        this.mainController = mainController;

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
    }

    public MenuView getView() {
        if (view == null)
            view = new MenuView(activity, this);
        return view;
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        int i;
        for (i = 0; i < menu.getCount(); i++) {
            canvas.drawText(menu.getItemDescription(i), 10,
                    MenuView.ITEM_SIZE + MenuView.ITEM_SIZE * i, paint);
        }
        if(menu.withExit())
            canvas.drawText("Exit", 10,
                    MenuView.ITEM_SIZE + MenuView.ITEM_SIZE * i, paint);
        if(menu.withMoney())
            canvas.drawText("Money: " + mainController.getGameController().getPlayer().getMoney(), 700,
                    MenuView.ITEM_SIZE, paint);
    }

    public void select(int item) {
        boolean result = false;
        if (item < menu.getCount())
            result = menu.select(item);
        if(menu.withExit()) {
            if (item == menu.getCount() || result)
                if (menu.getMenuMode() > 0) {
                    System.out.println("reset");
                    menu.resetMenuMode();
                } else {
                    System.out.println("close");
                    mainController.closeMenu();
                }
        }else{
            if (result)
                mainController.closeMenu();
        }
    }

    public void updateMenu(Menu menu) {
        this.menu = menu;
    }
}
