package com.neschur.kb2.app.models;

import java.io.Serializable;
import java.util.Random;

public class Magic implements Serializable {
    private final int[] campingMagics = {0, 0, 0, 0, 0, 0, 0};
    private final int[] battleMagics = {0, 0, 0, 0, 0, 0, 0};
    private final Random rand = new Random();
    private int magicPower;
    private int magicMaxCount;
    private int tornado = 0;

    public Magic(int magicPower, int magicMaxCount) {
        this.magicPower = magicPower;
        this.magicMaxCount = magicMaxCount;
    }

    public int getCampingMagic(int n) {
        return campingMagics[n];
    }

    public void upRandomMagic() {
        if (rand.nextInt(2) == 0)
            campingMagics[rand.nextInt(7)]++;
        else
            battleMagics[rand.nextInt(7)]++;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void upMagicPower() {
        this.magicPower++;
    }

    public int getMagicMaxCount() {
        return magicMaxCount;
    }

    public void upMagicMaxCount() {
        this.magicMaxCount += 1;
    }

    public int getTornado() {
        return tornado;
    }

    public void changeTornado(int d) {
        this.tornado += d;
    }
}
