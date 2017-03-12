package by.siarhei.kb2.app.entities;

import com.neschur.kb2.app.R;
import by.siarhei.kb2.app.models.MapPoint;

public class CastleRight extends EntityImpl {
    public CastleRight(MapPoint point) {
        super(point);
    }

    @Override
    public int getID() {
        return R.drawable.castle_r;
    }
}

