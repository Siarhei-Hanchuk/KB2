package by.siarhei.kb2.app.entities;

import com.neschur.kb2.app.R;
import by.siarhei.kb2.app.models.MapPoint;

public class GuidePost extends EntityImpl {

    public GuidePost(MapPoint point) {
        super(point);
    }

    @Override
    public int getID() {
        return R.drawable.guidepost;
    }
}
