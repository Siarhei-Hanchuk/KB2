package com.neschur.kb2.app.models;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.countries.World;

public class GameGrid {
    private Player player;
    private int[][] background = new int[5][5];
    private int[][] grid = new int[6][5];

    public GameGrid(GameController gameController) {
        player = gameController.getPlayer();
    }

    public void update() {
        generate();
    }

    public int getImageBuyXY(int x, int y) {
        return grid[x][y];
    }
    public int getBackgroundBuyXY(int x, int y) {
        return background[x][y];
    }

    private void generate() {
        int x = player.getX();
        int y = player.getY();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                MapPoint mp = player.getCountry().getMapPoint(x + (i - 2), y + (j - 2));
                if (mp.getDrawable() == R.drawable.forest) {
                    grid[i][j] = getForest(x + (i - 2), y + (j - 2));
                    background[i][j] = R.drawable.land;
                } else {
                    grid[i][j] = mp.getDrawable();
                    background[i][j] = mp.getLand();
                }
            }
        }
        if (player.inNave())
            grid[2][2] = R.drawable.nave;
        else
            grid[2][2] = R.drawable.player;

        makeStatus();
    }

    private int getForest(int x, int y) {
        MapPoint[][] mps = player.getCountry().getMapPoints();
        int nForestCount = 0;
        nForestCount = (mps[x - 1][y].getLand() == R.drawable.forest) ?
                nForestCount + 1 : nForestCount;
        nForestCount = (mps[x + 1][y].getLand() == R.drawable.forest) ?
                nForestCount + 1 : nForestCount;
        nForestCount = (mps[x][y - 1].getLand() == R.drawable.forest) ?
                nForestCount + 1 : nForestCount;
        nForestCount = (mps[x][y + 1].getLand() == R.drawable.forest) ?
                nForestCount + 1 : nForestCount;
        if(nForestCount == 0) {
            return R.drawable.forest;
        } else if (nForestCount == 4) {
            return R.drawable.forest_4;
        } else if (nForestCount == 1) {
            if (mps[x - 1][y].getLand() == R.drawable.forest) {
                return R.drawable.forest_1c;
            }
            if (mps[x + 1][y].getLand() == R.drawable.forest) {
                return R.drawable.forest_1a;
            }
            if (mps[x][y - 1].getLand() == R.drawable.forest) {
                return R.drawable.forest_1b;
            }
            if (mps[x][y + 1].getLand() == R.drawable.forest) {
                return R.drawable.forest_1d;
            }
        } else if (nForestCount == 3) {
            if (mps[x - 1][y].getLand() != R.drawable.forest) {
                return R.drawable.forest_3d;
            }
            if (mps[x + 1][y].getLand() != R.drawable.forest) {
                return R.drawable.forest_3b;
            }
            if (mps[x][y - 1].getLand() != R.drawable.forest) {
                return R.drawable.forest_3c;
            }
            if (mps[x][y + 1].getLand() != R.drawable.forest) {
                return R.drawable.forest_3a;
            }
        } else if (nForestCount == 2) {
            if (mps[x + 1][y].getLand() == R.drawable.forest &&
                    mps[x][y - 1].getLand() == R.drawable.forest) {
                return R.drawable.forest_2a;
            }
            if (mps[x - 1][y].getLand() == R.drawable.forest &&
                    mps[x][y - 1].getLand() == R.drawable.forest) {
                return R.drawable.forest_2b;
            }
            if (mps[x - 1][y].getLand() == R.drawable.forest &&
                    mps[x][y + 1].getLand() == R.drawable.forest) {
                return R.drawable.forest_2c;
            }
            if (mps[x + 1][y].getLand() == R.drawable.forest &&
                    mps[x][y + 1].getLand() == R.drawable.forest) {
                return R.drawable.forest_2d;
            }
            if (mps[x - 1][y].getLand() == R.drawable.forest &&
                    mps[x + 1][y].getLand() == R.drawable.forest) {
                return R.drawable.forest_2f;
            }
            if (mps[x][y - 1].getLand() == R.drawable.forest &&
                    mps[x][y + 1].getLand() == R.drawable.forest) {
                return R.drawable.forest_2e;
            }
        }
        return 0;
    }

    private void makeStatus() {
        grid[5][0] = R.drawable.status_contract_0;
        if (!player.isWallkick())
            grid[5][1] = R.drawable.status_wallkick_0;
        else
            grid[5][1] = R.drawable.status_wallkick_1;

        grid[5][2] = R.drawable.status_magic_0;

        int money = player.getMoney();
        if (money < 2000)
            grid[5][3] = R.drawable.status_money_0;
        else if (money < 5000)
            grid[5][3] = R.drawable.status_money_2000;
        else if (money < 10000)
            grid[5][3] = R.drawable.status_money_5000;
        else if (money < 20000)
            grid[5][3] = R.drawable.status_money_10000;
        else if (money < 50000)
            grid[5][3] = R.drawable.status_money_20000;
        else if (money < 75000)
            grid[5][3] = R.drawable.status_money_50000;
        else if (money < 100000)
            grid[5][3] = R.drawable.status_money_75000;
        else if (money < 150000)
            grid[5][3] = R.drawable.status_money_100000;
        else if (money < 200000)
            grid[5][3] = R.drawable.status_money_150000;
        else if (money < 500000)
            grid[5][3] = R.drawable.status_money_200000;
        else
            grid[5][3] = R.drawable.status_money_500000;

        grid[5][4] = R.drawable.status_ancientmap_0;

    }

}
