package by.siarhei.kb2.app.entities;

import by.siarhei.kb2.app.models.MapPoint;
import by.siarhei.kb2.app.models.Player;

public interface Entity {
    int getID();

    default int getID(Player player) {
        return getID();
    }

//    void destroy();
//
//    MapPoint getMapPoint();
//
//    void setMapPoint(MapPoint point);
}
