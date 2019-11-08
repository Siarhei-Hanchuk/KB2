package by.siarhei.kb2.app.server.models;

import by.siarhei.kb2.app.server.entities.Entity;

import java.io.Serializable;
import java.util.Random;

public class Mover implements Serializable {
    private final Glade glade;

    public Mover(Glade glade) {
        this.glade = glade;
    }

    private boolean teleport(Entity entity, MapPoint from, int x, int y) {
        // TODO:
        return glade.inBorders(x, y) && teleport(entity, from, glade.getMapPoint(x, y));
    }

    public boolean teleport(Entity entity, MapPoint from, MapPoint to) {
        if (to.isLand() && !to.isEntity()) {
            from.setEntity(null);
            to.setEntity(entity);
            return true;
        }
        return false;
    }

    private void moveInDirection(Entity entity, MapPoint from, MapPoint to) {
        int x = from.getX();
        int y = from.getY();
        int directionX = (int) Math.signum(to.getX() - x);
        int directionY = (int) Math.signum(to.getY() - y);
        if (!teleport(entity, from, x + directionX, y + directionY)) {
            if (directionX == 0)
                if (!teleport(entity, from, x + 1, y + directionY))
                    teleport(entity, from, x - 1, y + directionY);
            if (directionY == 0)
                if (!teleport(entity, from,x + directionX, y + 1))
                    teleport(entity, from,x + directionX, y - 1);
        }
    }

    public boolean canMoveTo(MapPoint point) {
        return (point.getEntity() == null && point.isLand());
    }

    public void moveInRandomDirection(Entity entity, MapPoint from) {
        Random random = new Random();
        MapPoint to = glade.getMapPoint(from.getX() + (-1 + random.nextInt(3)), from.getY() + (-1 + random.nextInt(3)));
        moveInDirection(entity, from, to);
    }
}
