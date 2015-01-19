package com.neschur.kb2.app.models;

import com.neschur.kb2.app.countries.Country;

public class Memory {
    private boolean map[][][] = new boolean[5][Country.MAX_MAP_SIZE][Country.MAX_MAP_SIZE];

    public Memory() {
        clear();
    }

    public void clear() {
        for (int n = 0; n < 5; n++) {
            for (int i = 0; i < Country.MAX_MAP_SIZE; i++) {
                for (int j = 0; j < Country.MAX_MAP_SIZE; j++) {
                    map[n][i][j] = false;
                }
            }
        }
    }

    public void update(int countryId, int x, int y) {
        for (int i = x - 2; i <= x + 2; i++) {
            for (int j = y - 2; j <= y + 2; j++) {
                map[countryId][i][j] = true;
            }
        }
    }

    public boolean[][] getMap(int countryId) {
        return map[countryId];
    }
}
