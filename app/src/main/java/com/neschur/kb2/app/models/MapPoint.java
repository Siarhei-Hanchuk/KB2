package com.neschur.kb2.app.models;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.Entity;

import java.io.Serializable;

public class MapPoint implements Serializable {
    private int land;
    private Entity entity;
    private Glade glade;
    private int x;
    private int y;

    public MapPoint(Glade glade, int x, int y) {
        this.glade = glade;
        this.x = x;
        this.y = y;
        land = -1;
        entity = null;
    }

    public int getLand() {
        return land;
    }

    public void setLand(int land) {
        this.land = land;
    }

    public int getDrawable() {
        if (this.entity == null) {
            return land;
        } else {
            return entity.getID();
        }
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Glade getGlade() {
        return glade;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean isLand() {
        return land == R.drawable.land;
    }

    public boolean isEntity() {
        return entity != null;
    }
}