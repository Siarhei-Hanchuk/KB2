package by.siarhei.kb2.app.server.entities;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.countries.Country;
import by.siarhei.kb2.app.server.models.MapPoint;

public class Nave implements Entity {
    @Override
    public int getID() {
        return R.drawable.nave;
    }

//    public void move(int x, int y, Country country) {
//        // TODO:
////        point.setEntity(null);
////        point = country.getMapPoint(x, y);
////        point.setEntity(this);
//    }
}
