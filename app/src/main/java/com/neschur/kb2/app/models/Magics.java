package com.neschur.kb2.app.models;

import java.io.Serializable;
import java.util.Random;

public class Magics implements Serializable {
    private final int[] campingMagics = {0, 0, 0, 0, 0, 0, 0};
    private final int[] battleMagics = {0, 0, 0, 0, 0, 0, 0};
    private final Random rand = new Random();

    public int getCampingMagic(int n) {
        return campingMagics[n];
    }

    public void upRandomMagic() {
        if (rand.nextInt(2) == 0)
            campingMagics[rand.nextInt(7)]++;
        else
            battleMagics[rand.nextInt(7)]++;
    }
}
