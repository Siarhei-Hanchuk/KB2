package by.siarhei.kb2.app.server.models.battle;

import java.util.Iterator;

import by.siarhei.kb2.app.R;

public class MapPoint extends by.siarhei.kb2.app.server.models.MapPoint {
    private boolean move = false;
    private Entity entity;

    public MapPoint(int x, int y) {
        super(x, y);
    }

    public boolean isMovable() {
        return move;
    }

    public void setMovable(boolean move) {
        this.move = move;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void setEntity(by.siarhei.kb2.app.server.entities.Entity entity) {
        this.entity = (Entity) entity;
    }

    public boolean isLand() {
        return getLand() == R.drawable.land;
    }

    public boolean isPlayerEntity() {
        return isEntity() && getEntity().isPlayerEntity();
    }

    public Iterator<MapPoint> getMapPointsList() {
        final MapPoint self = this;

        return new Iterator<MapPoint>() {
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public MapPoint next() {
                hasNext = false;
                return self;
            }

            @Override
            public void remove() {
            }
        };
    }
}
