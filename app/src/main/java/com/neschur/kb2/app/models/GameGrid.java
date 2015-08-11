package com.neschur.kb2.app.models;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public class GameGrid {
    public static final int STEP_X = 6;
    public static final int STEP_Y = 5;
    private final int[][] background = new int[STEP_Y][STEP_Y];
    private final int[][] grid = new int[STEP_X][STEP_Y];
    private final Player player;
    private int mode = 0;

    public GameGrid(Game game) {
        player = game.getPlayer();
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
        for (int i = 0; i < STEP_Y; i++) {
            for (int j = 0; j < STEP_Y; j++) {
                MapPoint mp = player.getCountry().getMapPoint(x + (i - 2), y + (j - 2));
                if (mp.getEntity() != null) {
                    grid[i][j] = mp.getEntity().getID();
                } else {
                    grid[i][j] = -1;
                }
                if (mp.getLand() == R.drawable.forest ||
                        mp.getLand() == R.drawable.water ||
                        mp.getLand() == R.drawable.sand ||
                        mp.getLand() == R.drawable.stone) {
                    background[i][j] = getComplexLand(x + (i - 2), y + (j - 2), mp.getLand());
                } else {
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

    private int drawableReflection(String drawable) {
        try {
            return R.drawable.class.getField(drawable).getInt(new R.drawable());
        } catch (NoSuchFieldException e) {
            return 0;
        } catch (IllegalAccessException e) {
            return 0;
        }
    }

    private int getComplexLand(int x, int y, int landId) {
        if (landId == R.drawable.forest) {
            return getComplexLand(x, y, "forest");
        } else if (landId == R.drawable.water) {
            return getComplexLand(x, y, "water");
        } else if (landId == R.drawable.sand) {
            return getComplexLand(x, y, "sand");
        } else if (landId == R.drawable.stone) {
            return getComplexLand(x, y, "stone");
        }
        return 0;
    }

    private int getComplexLand(int x, int y, String land) {
        MapPoint[][] mps = player.getCountry().getMapPoints();
        int landId = drawableReflection(land);
        int nLandCount = 0;
        if (x > 0 && x < Country.MAX_MAP_SIZE - 1 && y > 0 && y < Country.MAX_MAP_SIZE - 1) {
            nLandCount = (mps[x - 1][y].getLand() == landId) ?
                    nLandCount + 1 : nLandCount;
            nLandCount = (mps[x + 1][y].getLand() == landId) ?
                    nLandCount + 1 : nLandCount;
            nLandCount = (mps[x][y - 1].getLand() == landId) ?
                    nLandCount + 1 : nLandCount;
            nLandCount = (mps[x][y + 1].getLand() == landId) ?
                    nLandCount + 1 : nLandCount;
        } else {
            nLandCount = 4;
        }
        if (nLandCount == 0) {
            return drawableReflection(land + "_0");
        } else if (nLandCount == 4) {
            return landId;
        } else if (nLandCount == 1) {
            if (mps[x - 1][y].getLand() == landId) {
                return drawableReflection(land + "_1c");
            }
            if (mps[x + 1][y].getLand() == landId) {
                return drawableReflection(land + "_1a");
            }
            if (mps[x][y - 1].getLand() == landId) {
                return drawableReflection(land + "_1b");
            }
            if (mps[x][y + 1].getLand() == landId) {
                return drawableReflection(land + "_1d");
            }
        } else if (nLandCount == 3) {
            if (mps[x - 1][y].getLand() != landId) {
                return drawableReflection(land + "_3d");
            }
            if (mps[x + 1][y].getLand() != landId) {
                return drawableReflection(land + "_3b");
            }
            if (mps[x][y - 1].getLand() != landId) {
                return drawableReflection(land + "_3c");
            }
            if (mps[x][y + 1].getLand() != landId) {
                return drawableReflection(land + "_3a");
            }
        } else if (nLandCount == 2) {
            if (mps[x + 1][y].getLand() == landId &&
                    mps[x][y - 1].getLand() == landId) {
                return drawableReflection(land + "_2a");
            }
            if (mps[x - 1][y].getLand() == landId &&
                    mps[x][y - 1].getLand() == landId) {
                return drawableReflection(land + "_2b");
            }
            if (mps[x - 1][y].getLand() == landId &&
                    mps[x][y + 1].getLand() == landId) {
                return drawableReflection(land + "_2c");
            }
            if (mps[x + 1][y].getLand() == landId &&
                    mps[x][y + 1].getLand() == landId) {
                return drawableReflection(land + "_2d");
            }
            if (mps[x - 1][y].getLand() == landId &&
                    mps[x + 1][y].getLand() == landId) {
                return drawableReflection(land + "_2f");
            }
            if (mps[x][y - 1].getLand() == landId &&
                    mps[x][y + 1].getLand() == landId) {
                return drawableReflection(land + "_2e");
            }
        }
        return 0;
    }

    private void makeStatus() {
        switch (mode) {
            case 0:
                makeStatus0();
                break;
            case 1:
                makeStatus1();
                break;
            case 2:
                makeStatus2();
                break;
            case 3:
                makeStatus3();
                break;
        }
    }

    private void makeStatus0() {
        grid[5][0] = R.drawable.status_contract_0;
        grid[5][1] = R.drawable.status_workers;

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

    private void makeStatus1() {
        grid[5][0] = R.drawable.status_army;
        grid[5][1] = R.drawable.status_week;
        grid[5][2] = R.drawable.status_contract_0;
        grid[5][3] = R.drawable.status_shipment;
        grid[5][4] = R.drawable.status_back;
    }

    private void makeStatus2() {
        grid[5][0] = R.drawable.status_magic;
        grid[5][1] = R.drawable.status_tornado;
        grid[5][2] = R.drawable.guidepost;
        grid[5][3] = R.drawable.guidepost;
        grid[5][4] = R.drawable.status_back;
    }

    private void makeStatus3() {
        grid[5][0] = R.drawable.status_map;
        grid[5][1] = R.drawable.status_countries;
        grid[5][2] = R.drawable.status_back;
        grid[5][3] = -1;
        grid[5][4] = -1;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
