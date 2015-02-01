package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.Mover;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.models.MapPoint;

public class Sorcerer extends EntityImpl implements Moving {
    private Mover mover;

    public Sorcerer(MapPoint point) {
        super(point);
        mover = new Mover(point.getGlade());
    }

    @Override
    public int getID() {
        return R.drawable.sorcerer;
    }

    @Override
    public void moveTo(MapPoint point) {
        mover.moveTo(this, point);
    }
}
