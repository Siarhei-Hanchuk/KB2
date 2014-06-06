package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.World;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.objs.Nave;

/**
 * Created by siarhei on 6.6.14.
 */
public class MainController {
    private MainActivity activity;
    private World world;
    private Player player;
    private Nave nave;
    private Boolean inNave;

    public MainController(MainActivity activity) {
        this.activity = activity;
    }

    public void start(){
        world = new World();
        player = new Player();

        paint();
    }

    private void paint(){
        int x = player.getX();
        int y = player.getY();
        int[][] screen = new int[6][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                screen[i][j] = world.getCountry(player.getCountry()).getMap(x + (i - 2), y + (j - 2)).getDrawable();
            }
        }
        screen[2][2] = R.drawable.player;
        makeStatus(screen);

        activity.paint(screen);
    }

    private void makeStatus(int[][] screen){
        screen[5][0] = R.drawable.status_contract_0;

        if(player.isWallkick())
            screen[5][1] = R.drawable.status_wallkick_0;
        else
            screen[5][1] = R.drawable.status_wallkick_1;

        screen[5][2] = R.drawable.status_magic_0;

        int money = player.getMoney();
        if(money < 2000)
            screen[5][3]=R.drawable.status_money_0;
        else if(money < 5000)
            screen[5][3]=R.drawable.status_money_2000;
        else if(money < 10000)
            screen[5][3]=R.drawable.status_money_5000;
        else if(money < 20000)
            screen[5][3]=R.drawable.status_money_10000;
        else if(money < 50000)
            screen[5][3]=R.drawable.status_money_20000;
        else if(money < 75000)
			screen[5][3]=R.drawable.status_money_50000;
        else if(money < 100000)
			screen[5][3]=R.drawable.status_money_75000;
        else if(money < 150000)
			screen[5][3]=R.drawable.status_money_100000;
        else if(money < 200000)
			screen[5][3]=R.drawable.status_money_150000;
        else if(money < 500000)
			screen[5][3]=R.drawable.status_money_200000;
        else
            screen[5][3]=R.drawable.status_money_500000;

		screen[5][4] = R.drawable.status_ancientmap_0;
    }

    public void move(int dx, int dy){
        int x = player.getX();
        int y = player.getY();
        if(x + dx<2 || x + dx>62 || y + dy<2 || y + dy>62) {
            return;
        }

        MapPoint mp = world.getCountry(player.getCountry()).getMap(x + dx, y + dy);

        if(mp.obj==null) {
            if (inNave){
                if (mp.land == R.drawable.land || mp.land == R.drawable.sand) {
                    inNave = false;
                    player.move(x + dx, y + dy);
                }
                if (mp.land == R.drawable.water) {
                    nave.move(x + dx, y + dy);
                    player.move(x + dx, y + dy);
                }
            } else {
                if (mp.land == R.drawable.land || mp.land == R.drawable.plot
                        || mp.land == R.drawable.sand) {
                    player.move(x + dx, y + dy);
                }
            }
        }

        paint();
    }
}
