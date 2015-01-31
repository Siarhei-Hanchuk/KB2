package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.MapPoint;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    protected MapPoint point;

    public Entity(MapPoint point) {
        point.setEntity(this);
    }

    public abstract int getID();

    public void destroy() {
        point.setEntity(null);
    }

    public Country getCountry() {
        if (point.getGlade() instanceof Country)
            return (Country) point.getGlade();
        return null;
    }

    public int getX() {
        return point.getX();
    }

    public int getY() {
        return point.getY();
    }

    public MapPoint getMapPoint() {
        return point;
    }
}
