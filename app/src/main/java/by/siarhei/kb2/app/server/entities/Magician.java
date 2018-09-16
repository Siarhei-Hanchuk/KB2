package by.siarhei.kb2.app.server.entities;

import by.siarhei.kb2.app.R;

public class Magician extends EntityImpl {
    private static final int[] usedMagicianCount = {0, 0, 0, 0, 0};

    public Magician() {
        super();
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
