package com.neschur.kb2.app.entities;

/**
 * Created by siarhei on 2.6.14.
 */

import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.ui.Menu;

public abstract class Entity {
    private Country country;
    protected int x;
    protected int y;

    public Entity(Country country, int x, int y) {
        country.getMap(x, y).setEntity(this);
        this.country=country;
        this.x=x;
        this.y=y;
    }

    public void delete(){
        country.getMap(x, y).setEntity(null);
    }

    public void step(int x,int y){
        move(this.x+(int)Math.signum(x),this.y+(int)Math.signum(y));
    }

    public void move(int x,int y){
        delete();
        this.x=x;
        this.y=y;
        country.getMap(x, y).setEntity(this);
    }

    public Menu getMenu(GameController gameController){
        return null;
    }

    public abstract int getID();
    public /*abstract*/ void ncAction(){

    }
}

