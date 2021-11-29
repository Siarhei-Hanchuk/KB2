package by.siarhei.kb2.app.server.entities;

import by.siarhei.kb2.app.R;

public class Magician implements Entity {
//    private static final int[] usedMagicianCount = {0, 0, 0, 0, 0};

    @Override
    public int getID() {
        return R.drawable.magician;
    }

//    public int getUsedMagicianCount(int countryId) {
//        return usedMagicianCount[countryId];
//    }
//
//    public void upUsedMagicianCount(int countryId) {
//        usedMagicianCount[countryId]++;
//    }
}
