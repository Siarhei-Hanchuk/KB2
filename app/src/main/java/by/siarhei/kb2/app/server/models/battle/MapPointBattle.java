package by.siarhei.kb2.app.server.models.battle;

import java.util.Iterator;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.models.MapPoint;

public class MapPointBattle extends MapPoint {
    private boolean move = false;
    private WarriorEntity entity;

    public MapPointBattle(int x, int y) {
        super(x, y);
    }

    public boolean isMovable() {
        return move;
    }

    public void setMovable(boolean move) {
        this.move = move;
    }

    @Override
    public WarriorEntity getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Entity entity) {
        this.entity = (WarriorEntity) entity;
    }

    public boolean isLand() {
        return getLand() == R.drawable.land;
    }

    public boolean isPlayerEntity() {
        return isEntity() && getEntity().isPlayerEntity();
    }

    public Iterator<MapPointBattle> getMapPointsList() {
        final MapPointBattle self = this;

        return new Iterator<MapPointBattle>() {
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public MapPointBattle next() {
                hasNext = false;
                return self;
            }

            @Override
            public void remove() {
            }
        };
    }
}
