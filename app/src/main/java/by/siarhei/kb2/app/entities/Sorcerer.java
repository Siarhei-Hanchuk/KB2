package by.siarhei.kb2.app.entities;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.models.MapPoint;
import by.siarhei.kb2.app.models.Mover;

public class Sorcerer extends EntityImpl {
    public Sorcerer(MapPoint point) {
        super(point);
    }

    @Override
    public int getID() {
        return R.drawable.sorcerer;
    }
}
