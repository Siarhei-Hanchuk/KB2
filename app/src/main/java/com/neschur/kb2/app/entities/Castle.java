package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.MapPoint;

public class Castle extends Entity {
    private int id;

    public Castle(MapPoint point, int id) {
        super(point);
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }
}
