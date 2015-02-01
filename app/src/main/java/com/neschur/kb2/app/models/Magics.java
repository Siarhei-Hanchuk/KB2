package com.neschur.kb2.app.models;

import java.io.Serializable;

public class Magics implements Serializable {
    private final int[] magics = {0, 0, 0, 0, 0, 0, 0};

    public int getMagic(int n) {
        return magics[n];
    }
}
