package com.neschur.kb2.app.models;

import com.neschur.kb2.app.entities.Entity;

import java.io.Serializable;
import java.util.Random;

public class Mover implements Serializable {
    private final Glade glade;

    public Mover(Glade glade) {
        this.glade = glade;
    }

    private boolean teleport(Entity entity, int x, int y) {
        if (!glade.inBorders(x, y))
            return false;
        return teleport(entity, glade.getMapPoint(x, y));
    }

    public boolean teleport(Entity entity, MapPoint to) {
        MapPoint from = entity.getMapPoint();
        if (to.isLand() && !to.isEntity()) {
            from.setEntity(null);
            to.setEntity(entity);
            entity.setMapPoint(to);
            return true;
        }
        return false;
    }

    public void moveInDirection(Entity entity, MapPoint to) {
        int x = entity.getMapPoint().getX();
        int y = entity.getMapPoint().getY();
        int directionX = (int) Math.signum(to.getX() - x);
        int directionY = (int) Math.signum(to.getY() - y);
        if (!teleport(entity, x + directionX, y + directionY)) {
            if (directionX == 0)
                if (!teleport(entity, x + 1, y + directionY))
                    teleport(entity, x - 1, y + directionY);
            if (directionY == 0)
                if (!teleport(entity, x + directionX, y + 1))
                    teleport(entity, x + directionX, y - 1);
        }
    }

    public void moveInRandomDirection(Entity entity) {
        Random random = new Random();
        MapPoint from = entity.getMapPoint();
        MapPoint to = glade.getMapPoint(from.getX() +(-1 + random.nextInt(3)), from.getY() +(-1 + random.nextInt(3)));
        moveInDirection(entity, to);
    }
}
