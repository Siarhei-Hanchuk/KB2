package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.models.MapPoint;

public class Sorcerer extends EntityImpl {

    public Sorcerer(MapPoint point) {
        super(point);
    }

    @Override
    public int getID() {
        return R.drawable.sorcerer;
    }
}
