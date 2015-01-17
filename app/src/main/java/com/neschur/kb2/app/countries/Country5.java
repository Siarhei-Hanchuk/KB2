package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

public class Country5 extends Country {

    public Country5(int id) {
        super(id);
        sand();
        river(10);
        cities();
        stones();
        goldChests(100);
    }

    protected int goldChestMax() {
        return 6380;
    }

    protected int goldChestMin() {
        return 2380;
    }

    private void sand() {
        for (int i = 6; i < 59; i++) {
            for (int j = 6; j < 11; j++) {
                map[i][j].land = R.drawable.sand;
                map[j][i].land = R.drawable.sand;
            }
        }
        for (int i = 6; i < 59; i++) {
            for (int j = 54; j < 59; j++) {
                map[j][i].land = R.drawable.sand;
                map[i][j].land = R.drawable.sand;
            }
        }
    }

    void stones() {
        for (int i = 11; i < MAX_MAP_SIZE - 11; i++) {
            for (int j = 11; j < MAX_MAP_SIZE - 11; j++) {
                if ((map[i][j].getEntity() == null) && (map[i][j].land == R.drawable.land)) {
                    int r = rand(30);
                    if (r < 16) {
                        map[i][j].land = R.drawable.stone;
                    }
                }
            }
        }
    }
}
