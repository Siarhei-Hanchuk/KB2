package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.Glade;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    protected int x;
    protected int y;
    protected Glade glade;

    public Entity(Glade glade, int x, int y) {
        glade.getMapPoint(x, y).setEntity(this);
        this.glade = glade;
        this.x = x;
        this.y = y;
    }

    public abstract int getID();

    public void destroy() {
        glade.getMapPoint(x, y).setEntity(null);
    }

    public void step(int x, int y) {
        move(this.x + (int) Math.signum(x), this.y + (int) Math.signum(y));
    }

    public void moveTo(int x, int y) {
        int directionX = (int) Math.signum(x - this.x);
        int directionY = (int) Math.signum(y - this.y);
        move(this.x + directionX, this.y + directionY);
    }

    public void move(int x, int y) {
        if (glade.getMapPoint(x, y).getEntity() == null &&
                glade.getMapPoint(x, y).getLand() == R.drawable.land) {
            destroy();
            this.x = x;
            this.y = y;
            glade.getMapPoint(x, y).setEntity(this);
        }
    }

    public Country getCountry() {
        if (glade instanceof Country)
            return (Country)glade;
        return null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

