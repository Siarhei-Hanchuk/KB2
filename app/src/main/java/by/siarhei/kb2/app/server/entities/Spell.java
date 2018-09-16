package by.siarhei.kb2.app.server.entities;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.models.MapPoint;

public class Spell extends EntityImpl {
    public Spell(MapPoint point) {
        super();
    }

    @Override
    public int getID() {
        return R.drawable.map;
    }
}
