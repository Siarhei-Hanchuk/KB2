package by.siarhei.kb2.app.entities;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.models.MapPoint;
import by.siarhei.kb2.app.models.Mover;

public class Magician extends EntityImpl {
    private static final int[] usedMagicianCount = {0, 0, 0, 0, 0};

    public Magician(MapPoint point) {
        super(point);
    }

    @Override
    public int getID() {
        return R.drawable.magician;
    }

    public int getUsedMagicianCount(int countryId) {
        return usedMagicianCount[countryId];
    }

    public void upUsedMagicianCount(int countryId) {
        usedMagicianCount[countryId]++;
    }
}
