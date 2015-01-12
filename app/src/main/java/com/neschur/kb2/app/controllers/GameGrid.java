package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.World;
import com.neschur.kb2.app.models.Player;

/**
 * Created by siarhei on 11.1.15.
 */
public class GameGrid {
    private Player player;
    private World world;
    private int[][] grid;

    public GameGrid(GameController gameController) {
        world = gameController.getWorld();
        player = gameController.getPlayer();
    }

    public void update() {
        grid = generate();
    }

    public int getBuyXY(int x, int y) {
        return grid[x][y];
    }

    private int[][] generate() {
        int x = player.getX();
        int y = player.getY();
        int[][] screen = new int[6][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                screen[i][j] = player.getCountry().getMap(x + (i - 2), y + (j - 2)).getDrawable();
            }
        }
        if(player.inNave())
            screen[2][2] = R.drawable.nave;
        else
            screen[2][2] = R.drawable.player;

        makeStatus(screen);

        return screen;
    }

    private void makeStatus(int[][] screen) {
        screen[5][0] = R.drawable.status_contract_0;
        System.out.println(player.isWallkick());
        if (!player.isWallkick())
            screen[5][1] = R.drawable.status_wallkick_0;
        else
            screen[5][1] = R.drawable.status_wallkick_1;

        screen[5][2] = R.drawable.status_magic_0;

        int money = player.getMoney();
        if (money < 2000)
            screen[5][3] = R.drawable.status_money_0;
        else if (money < 5000)
            screen[5][3] = R.drawable.status_money_2000;
        else if (money < 10000)
            screen[5][3] = R.drawable.status_money_5000;
        else if (money < 20000)
            screen[5][3] = R.drawable.status_money_10000;
        else if (money < 50000)
            screen[5][3] = R.drawable.status_money_20000;
        else if (money < 75000)
            screen[5][3] = R.drawable.status_money_50000;
        else if (money < 100000)
            screen[5][3] = R.drawable.status_money_75000;
        else if (money < 150000)
            screen[5][3] = R.drawable.status_money_100000;
        else if (money < 200000)
            screen[5][3] = R.drawable.status_money_150000;
        else if (money < 500000)
            screen[5][3] = R.drawable.status_money_200000;
        else
            screen[5][3] = R.drawable.status_money_500000;

        screen[5][4] = R.drawable.status_ancientmap_0;

    }

}
