package by.siarhei.kb2.app.server.models;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.models.iterators.MapPointsOwner;

import java.io.Serializable;
import java.util.Iterator;

public class MapPoint implements Serializable, MapPointsOwner {
    private final int x;
    private final int y;
    private int land;
    private Entity entity;

    public MapPoint(int x, int y) {
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

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean isLand() {
        return getLand() == R.drawable.land;
    }

    public void setLand(int land) {
        this.land = land;
    }

    public boolean isEntity() {
        return getEntity() != null;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public Iterator<MapPoint> getMapPointsList(Class type) {
        final MapPoint self = this;

        return new Iterator<MapPoint>() {
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                if(type.isInstance(entity))
                    return hasNext;
                else
                    return false;
            }

            @Override
            public MapPoint next() {
                hasNext = false;
                if(type.isInstance(entity))
                    return self;
                else
                    return null;
            }

            @Override
            public void remove() {
            }
        };
    }
}