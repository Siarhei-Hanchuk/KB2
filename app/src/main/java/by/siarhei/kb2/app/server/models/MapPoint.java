package by.siarhei.kb2.app.server.models;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.entities.Entity;

import java.io.Serializable;

public class MapPoint implements Serializable {
    private final Glade glade;
    private final int x;
    private final int y;
    private int land;
    private Entity entity;

    public MapPoint(Glade glade, int x, int y) {
        this.glade = glade;
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

//    public Glade getGlade() {
//        return glade;
//    }

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
}