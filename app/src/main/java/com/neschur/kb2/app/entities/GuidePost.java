package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.MapPoint;

public class GuidePost extends EntityImpl {

    public GuidePost(MapPoint point) {
        super(point);
    }

    @Override
    public int getID() {
        return R.drawable.guidepost;
    }
}
