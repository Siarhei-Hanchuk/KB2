package com.neschur.kb2.app.objs;

/**
 * Created by siarhei on 2.6.14.
 */

import com.neschur.kb2.app.countries.Country;

public abstract class Obj {
    private Country country;
    protected int x;
    protected int y;

    public Obj(Country country,int x, int y) {
        country.getMap(x, y).setObj(this);
        this.country=country;
        this.x=x;
        this.y=y;
    }

    public void delete(){
        country.getMap(x, y).setObj(null);
    }

    public void step(int x,int y){
        move(this.x+(int)Math.signum(x),this.y+(int)Math.signum(y));
    }

    public void move(int x,int y){
        delete();
        this.x=x;
        this.y=y;
        country.getMap(x, y).setObj(this);
    }

    public abstract int getID();
    public abstract int action();
    public /*abstract*/ void ncAction(){

    }
}

