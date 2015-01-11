package com.neschur.kb2.app.controllers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.ui.CityMenu;
import com.neschur.kb2.app.ui.Menu;
import com.neschur.kb2.app.views.Drawable;
import com.neschur.kb2.app.views.MenuView;

/**
 * Created by siarhei on 11.1.15.
 */
public class MenuController implements Drawable{
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
        if(view == null)
            view = new MenuView(activity, this);
        return view;
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        int i;
        for (i = 0; i < menu.getCount(); i++) {
            canvas.drawText(menu.getItemDescr(i), 10,
                    MenuView.ITEM_SIZE + MenuView.ITEM_SIZE*i, paint);
        }
        canvas.drawText("Exit", 10,
                MenuView.ITEM_SIZE + MenuView.ITEM_SIZE*i, paint);
    }

    public void select(int item) {
        if(item < menu.getCount())
            menu.select(item);
        else
            if(item == menu.getCount())
                mainController.closeMenu();
    }

    public void displayMenu(Menu menu) {
//        view.getHolder().lockCanvas();
    }

    public void updateMenu(CityMenu menu) {
        this.menu = menu;
    }
}
