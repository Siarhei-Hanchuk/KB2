package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.MapPoint;

public interface Entity {
    public int getID();
    public void destroy();
    public Country getCountry();
    public int getX();
    public int getY();
    public MapPoint getMapPoint();
}
