package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.models.MapPoint;

public class CastleLeft extends EntityImpl {
    public CastleLeft(MapPoint point) {
        super(point);
    }

    @Override
    public int getID() {
        return R.drawable.castle_l;
    }
}

