package com.neschur.kb2.app;

import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Glade;
import com.neschur.kb2.app.models.MapPoint;

public class Mover {
    private Glade glade;

    public Mover(Glade glade) {
        this.glade = glade;
    }

    public boolean teleport(Entity entity, MapPoint to) {
        MapPoint from = entity.getMapPoint();
        if (from.isLand() && !to.isEntity()) {
            from.setEntity(null);
            to.setEntity(entity);
            return true;
        }
        return false;
    }

    public void moveTo(Entity entity, MapPoint to) {
        int x = entity.getMapPoint().getX();
        int y = entity.getMapPoint().getY();
        int directionX = (int) Math.signum(x - to.getX());
        int directionY = (int) Math.signum(y - to.getY());
        if (!teleport(entity, glade.getMapPoint(to.getX() + directionX, to.getY() + directionY))) {
            if (directionX == 0)
                if (!teleport(entity, glade.getMapPoint(to.getX() + 1, to.getY() + directionY)))
                    teleport(entity, glade.getMapPoint(to.getX() - 1, to.getY() + directionY));
            if (directionX == 1)
                if (!teleport(entity, glade.getMapPoint(to.getX() + directionX, to.getY() + 1)))
                    teleport(entity, glade.getMapPoint(to.getX() + directionX, to.getY() - 1));
        }
    }
}
