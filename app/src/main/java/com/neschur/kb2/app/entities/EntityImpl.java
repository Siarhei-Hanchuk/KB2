package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.MapPoint;

import java.io.Serializable;

public abstract class EntityImpl implements Entity, Serializable {
    MapPoint point;

    EntityImpl(MapPoint point) {
        point.setEntity(this);
        this.point = point;
    }

    @Override
    public abstract int getID();

    @Override
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

    @Override
    public MapPoint getMapPoint() {
        return point;
    }

    @Override
    public void setMapPoint(MapPoint point) {
        this.point = point;
    }
}
