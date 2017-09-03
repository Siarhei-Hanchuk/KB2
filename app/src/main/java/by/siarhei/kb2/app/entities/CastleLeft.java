package by.siarhei.kb2.app.entities;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.models.MapPoint;

public class CastleLeft extends EntityImpl {
    public CastleLeft(MapPoint point) {
        super(point);
    }

    @Override
    public int getID() {
        return R.drawable.castle_l;
    }
}

