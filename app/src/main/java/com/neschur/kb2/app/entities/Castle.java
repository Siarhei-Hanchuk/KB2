package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.models.MapPoint;

public class Castle extends EntityImpl {
    private final int id;

    public Castle(MapPoint point, int id) {
        super(point);
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }
}
