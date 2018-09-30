package by.siarhei.kb2.app.server.models.iterators;

import java.util.Iterator;

import by.siarhei.kb2.app.server.models.MapPoint;

public interface MapPointsOwner {
    Iterator<MapPoint> getMapPointsList(Class type);
}
