package by.siarhei.kb2.app.entities;

import by.siarhei.kb2.app.models.MapPoint;

public interface Entity {
    int getID();

    void destroy();

    MapPoint getMapPoint();

    void setMapPoint(MapPoint point);
}
