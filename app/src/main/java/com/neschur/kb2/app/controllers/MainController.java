package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.World;
import com.neschur.kb2.app.models.Player;

/**
 * Created by siarhei on 6.6.14.
 */
public class MainController {
    private MainActivity activity;
    private World world;
    private Player player;

    public MainController(MainActivity activity) {
        this.activity = activity;
    }

    public void start(){
        this.world = new World();
        this.player = new Player();

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

        activity.paint(screen);
    }

    public void move(int dx, int dy){
        int x = player.getX();
        int y = player.getY();
        if(x + dx<2 || x + dx>62 || y + dy<2 || y + dy>62) {
            return;
        }
        player.move(x+dx, y+dy);
        paint();
    }
}
