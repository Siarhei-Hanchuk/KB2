package by.siarhei.kb2.app.entities;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.countries.Country;
import by.siarhei.kb2.app.models.MapPoint;

public class Nave extends EntityImpl {
    public Nave(MapPoint point) {
        super();
    }

    @Override
    public int getID() {
        return R.drawable.nave;
    }

    public void move(int x, int y, Country country) {
        // TODO:
//        point.setEntity(null);
//        point = country.getMapPoint(x, y);
//        point.setEntity(this);
    }
}
