package com.neschur.kb2.app.models;

import com.neschur.kb2.app.entities.Entity;

import java.io.Serializable;

public class MapPoint implements Serializable {
    private int land;
    private Entity entity;

    public MapPoint() {
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
}