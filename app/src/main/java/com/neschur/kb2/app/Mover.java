package com.neschur.kb2.app;

import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.Glade;
import com.neschur.kb2.app.models.MapPoint;

public class Mover {
    private Glade glade;

    public Mover(Glade glade) {
        this.glade = glade;
    }

    public boolean teleport(Entity entity, MapPoint from, MapPoint to) {
        if (glade.isLand(from.getX(), from.getY()) && !glade.isEntity(to.getX(), to.getY())) {
            from.setEntity(null);
            to.setEntity(entity);
            return true;
        }
        return false;
    }
}
