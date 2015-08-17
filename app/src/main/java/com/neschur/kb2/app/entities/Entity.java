package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.models.MapPoint;

public interface Entity {
    int getID();

    void destroy();

    MapPoint getMapPoint();

    void setMapPoint(MapPoint point);
}
