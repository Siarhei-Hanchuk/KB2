package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.models.MapPoint;

public interface Entity {
    public int getID();
    public void destroy();
    public MapPoint getMapPoint();
    public void setMapPoint(MapPoint point);
}
