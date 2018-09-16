package by.siarhei.kb2.app.server.entities;

import by.siarhei.kb2.app.server.models.MapPoint;

public interface Moving {
    void moveInDirection(MapPoint point);

    void moveInRandomDirection();

    boolean canMoveTo(MapPoint point);
}
