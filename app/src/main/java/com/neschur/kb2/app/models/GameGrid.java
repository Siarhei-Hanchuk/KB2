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
                grid[i][j] = mp.getDrawable();
                background[i][j] = mp.getLand();
            }
        }
        if (player.inNave())
            grid[2][2] = R.drawable.nave;
        else
            grid[2][2] = R.drawable.player;

        makeStatus();
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
