package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
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

    public void step(int x, int y) {
        move(point.getX() + (int) Math.signum(x), point.getY() + (int) Math.signum(y));
    }

    public void moveTo(int x, int y) {
        int directionX = (int) Math.signum(x - point.getX());
        int directionY = (int) Math.signum(y - point.getY());
        if(!move(point.getX() + directionX, point.getY() + directionY)) {
            if ( directionX == 0)
                if(!move(point.getX() + 1, point.getY() + directionY))
                    move(point.getX() - 1, point.getY() + directionY);
            if ( directionX == 1)
                if(!move(point.getX() + directionX, point.getY() + 1))
                    move(point.getX() + directionX, point.getY() - 1);
        }
    }

    public boolean move(int x, int y) {
        if (point.getGlade().getMapPoint(x, y).getEntity() == null &&
                point.getGlade().getMapPoint(x, y).getLand() == R.drawable.land) {
            destroy();
            point = point.getGlade().getMapPoint(x, y);
            point.setEntity(this);

            return true;
        }
        return false;
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
}
