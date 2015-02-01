package com.neschur.kb2.app.models;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.Entity;

import java.io.Serializable;

public class MapPoint implements Serializable {
    private final Glade glade;
    private final int x;
    private final int y;
    private int land;
    private Entity entity;

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

    public Entity getEntity() {
        return entity;
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

    public void setLand(int land) {
        this.land = land;
    }

    public boolean isEntity() {
        return entity != null;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}