package com.neschur.kb2.app.entities;

/**
 * Created by siarhei on 2.6.14.
 */

import android.app.Activity;

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.ui.Menu;

public abstract class Entity {
    private Country country;
    protected int x;
    protected int y;

    public Entity(Country country, int x, int y) {
        country.getMap(x, y).setEntity(this);
        this.country = country;
        this.x = x;
        this.y = y;
    }

    public abstract int getID();

    public void destroy() {
        country.getMap(x, y).setEntity(null);
    }

    public void step(int x, int y) {
        move(this.x + (int) Math.signum(x), this.y + (int) Math.signum(y));
    }

    public void move(int x, int y) {
        destroy();
        this.x = x;
        this.y = y;
        country.getMap(x, y).setEntity(this);
    }

    public Menu getMenu(Activity activity, GameController gameController) {
        return null;
    }
    public String getMessage(Activity activity) {
        return null;
    }
}

