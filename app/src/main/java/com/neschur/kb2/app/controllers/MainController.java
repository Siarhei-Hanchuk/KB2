package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.MainActivity;
import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.World;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.objs.City;
import com.neschur.kb2.app.objs.Nave;
import com.neschur.kb2.app.objs.Obj;

/**
 * Created by siarhei on 6.6.14.
 */
public class MainController {
    private MainActivity activity;
    private World world;
    private Player player;
    private UIController uiController;
    private Boolean inNave;

    public MainController(MainActivity activity) {
        this.activity = activity;
    }

    public void start(){
        world = new World();
        player = new Player();
        uiController = new UIController(activity, player, world);

        uiController.start();
    }

    public void move(int dx, int dy){
        int x = player.getX();
        int y = player.getY();
        if(x + dx<2 || x + dx>62 || y + dy<2 || y + dy>62) {
            return;
        }

        MapPoint mp = world.getCountry(player.getCountry()).getMap(x + dx, y + dy);

        if(mp.obj == null) {
            if (inNave){
                if (mp.land == R.drawable.land || mp.land == R.drawable.sand) {
                    inNave = false;
                    player.move(x + dx, y + dy);
                }
                if (mp.land == R.drawable.water) {
                    player.move(x + dx, y + dy);
                }
            } else {
                if (mp.land == R.drawable.land || mp.land == R.drawable.plot
                        || mp.land == R.drawable.sand) {
                    player.move(x + dx, y + dy);
                }
            }
        } else {
            actionWithObject(player, mp.obj);
        }

        uiController.paint();
    }

    private void actionWithObject(Player player, Obj obj){
        if(obj instanceof Nave){
            player.setNave((Nave)obj);
            player.move(((Nave)obj).getX(), ((Nave)obj).getY());
        }
        if(obj instanceof City) {
            City city = (City)obj;

        }
    }
}
