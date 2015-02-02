package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.MapPoint;

public class Nave extends EntityImpl {
    public Nave(MapPoint point) {
        super(point);
    }

    @Override
    public int getID() {
        return R.drawable.nave;
    }

    public void move(int x, int y) {
        move(x, y, null);
    }

    public void move(int x, int y, Country country) {
        point.setEntity(null);
        if (country != null)
            point = country.getMapPoint(x, y);
        else
            point = getCountry().getMapPoint(x, y);
        point.setEntity(this);
    }
}
