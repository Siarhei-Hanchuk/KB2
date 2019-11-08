package by.siarhei.kb2.app.server.entities;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.server.models.iterators.CitiesOwner;

import java.util.Iterator;
import java.util.Random;

public class City implements Entity, CitiesOwner {
    private final int nameId;
    private final Castle castle;
    private int[] workers;
    private int magic;

    public City(int nameId, Castle castle) {
        this.nameId = nameId;
        this.castle = castle;
        resetWorkers();
        resetMagic();
    }

    public void resetWorkers() {
        Random rand = new Random();
        workers = new int[]{rand.nextInt(8), rand.nextInt(8), rand.nextInt(8), rand.nextInt(8)};
    }

    @Override
    public int getID() {
        return R.drawable.city;
    }

    public int getWorkers(int id) {
        return workers[id];
    }

    public void changeWorkers(int id, int count) {
        workers[id] += count;
    }

    public int getNameId() {
        return nameId;
    }

    @Override
    public Iterator<City> getCities() {
        final City self = this;

        return new Iterator<City>() {
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public City next() {
                hasNext = false;
                return self;
            }

            @Override
            public void remove() {
            }
        };
    }

    private void resetMagic() {
        magic = (new Random()).nextInt(14);
    }

    public int getMagic() {
        return magic;
    }

    public Castle getCastle() {
        return castle;
    }
}
