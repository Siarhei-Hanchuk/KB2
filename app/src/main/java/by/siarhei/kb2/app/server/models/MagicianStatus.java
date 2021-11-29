package by.siarhei.kb2.app.server.models;

import java.io.Serializable;

public class MagicianStatus implements Serializable {
    private final int[] usedMagicianCount = {0, 0, 0, 0, 0};

    public int getUsedMagicianCount(int countryId) {
        return usedMagicianCount[countryId];
    }

    public void upUsedMagicianCount(int countryId) {
        usedMagicianCount[countryId]++;
    }
}
