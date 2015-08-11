package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.models.MapPoint;

public interface Moving {
    public void moveInDirection(MapPoint point);

    public void moveInRandomDirection();

    public boolean canMoveTo(MapPoint point);
}
