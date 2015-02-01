package com.neschur.kb2.app.models;

import com.neschur.kb2.app.entities.Entity;

public interface Glade {
    public MapPoint getMapPoint(int x, int y);

    public boolean isEntity(int x, int y);

    public boolean isLand(int x, int y);

    public Entity getEntity(int x, int y);
}
