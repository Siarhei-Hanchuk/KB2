package com.neschur.kb2.app.models;

import com.neschur.kb2.app.entities.Entity;

import java.io.Serializable;

public class Mover implements Serializable {
    private Glade glade;

    public Mover(Glade glade) {
        this.glade = glade;
    }

    public boolean teleport(Entity entity, MapPoint to) {
        MapPoint from = entity.getMapPoint();
        if (from.isLand() && !to.isEntity()) {
            from.setEntity(null);
            to.setEntity(entity);
            entity.setMapPoint(to);
            return true;
        }
        return false;
    }

    public void moveTo(Entity entity, MapPoint to) {
        int x = entity.getMapPoint().getX();
        int y = entity.getMapPoint().getY();
        int directionX = (int) Math.signum(to.getX() - x);
        int directionY = (int) Math.signum(to.getY() - y);
        if (!teleport(entity, glade.getMapPoint(x + directionX, y + directionY))) {
            if (directionX == 0)
                if (!teleport(entity, glade.getMapPoint(x + 1, y + directionY)))
                    teleport(entity, glade.getMapPoint(x - 1, y + directionY));
            if (directionY == 0)
                if (!teleport(entity, glade.getMapPoint(x + directionX, y + 1)))
                    teleport(entity, glade.getMapPoint(x + directionX, y - 1));
        }
    }
}
