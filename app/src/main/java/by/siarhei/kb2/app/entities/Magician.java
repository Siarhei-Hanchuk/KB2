package by.siarhei.kb2.app.entities;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.models.MapPoint;
import by.siarhei.kb2.app.models.Mover;

public class Magician extends EntityImpl implements Moving {
    private static final int[] usedMagicianCount = {0, 0, 0, 0, 0};
    private final Mover mover;

    public Magician(MapPoint point) {
        super(point);
        mover = new Mover(point.getGlade());
    }

    @Override
    public int getID() {
        return R.drawable.magician;
    }

    public int getUsedMagicianCount() {
        return usedMagicianCount[getCountry().getId()];
    }

    public void upUsedMagicianCount() {
        usedMagicianCount[getCountry().getId()]++;
    }

    @Override
    public void moveInDirection(MapPoint point) {
        mover.moveInDirection(this, point);
    }

    @Override
    public void moveInRandomDirection() {
        mover.moveInRandomDirection(this);
    }

    @Override
    public boolean canMoveTo(MapPoint point) {
        return mover.canMoveTo(point);
    }
}
