package by.siarhei.kb2.app.server.countries;

import by.siarhei.kb2.app.server.entities.ArmyShop;
import by.siarhei.kb2.app.server.entities.City;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.iterators.ArmyShopsOwner;
import by.siarhei.kb2.app.server.models.iterators.CitiesOwner;
import by.siarhei.kb2.app.server.models.iterators.EntityIterator;
import by.siarhei.kb2.app.server.models.iterators.MapPointsOwner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class World implements Serializable, ArmyShopsOwner, MapPointsOwner {
    private Country[] countries;

    public World(Country[] countries) {
        this.countries = countries;
    }

    public Country getCountry(int i) {
        return countries[i];
    }

    @Override
    public Iterator<ArmyShop> getArmyShops() {
        ArrayList<Iterator<ArmyShop>> iterators = new ArrayList<>();
        for (ArmyShopsOwner shop : countries) {
            iterators.add(shop.getArmyShops());
        }
        return new EntityIterator<>(iterators);
    }

    @Override
    public Iterator<MapPoint> getMapPointsList(Class type) {
        ArrayList<Iterator<MapPoint>> iterators = new ArrayList<>();
        for (MapPointsOwner mapPointsOwner: countries) {
            iterators.add(mapPointsOwner.getMapPointsList(type));
        }
        return new EntityIterator<>(iterators);
    }
}

