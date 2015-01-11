package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.World;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.Player;
import com.neschur.kb2.app.views.MainView;

/**
 * Created by siarhei on 11.1.15.
 */
public class GameController {
    private MainView mainView;
    private World world;
    private Player player;
    private Boolean inNave = false;

    public GameController() {
        world = new World();
        player = new Player();
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public void move(int dx, int dy){
        int x = player.getX();
        int y = player.getY();
        System.out.println(player.getX() + " " + player.getY());
        if(x + dx<2 || x + dx>62 || y + dy<2 || y + dy>62) {
            return;
        }

        MapPoint mp = world.getCountry(player.getCountry()).getMap(x + dx, y + dy);

        if(mp.obj == null) {
            if (inNave){
                System.out.println("a");
                if (mp.land == R.drawable.land || mp.land == R.drawable.sand) {
                    inNave = false;
                    player.move(x + dx, y + dy);
                }
                if (mp.land == R.drawable.water) {
                    player.move(x + dx, y + dy);
                }
            } else {
                System.out.println("b");
                System.out.println(mp.land);
                if (mp.land == R.drawable.land || mp.land == R.drawable.plot
                        || mp.land == R.drawable.sand) {
                    System.out.println("c");
                    player.move(x + dx, y + dy);
                }
            }
        } else {
//            actionWithObject(player, mp.obj);
        }
    }

//    private void actionWithObject(Player player, Obj obj){
//        if(obj instanceof Nave){
//            player.setNave((Nave)obj);
//            player.move(((Nave)obj).getX(), ((Nave)obj).getY());
//        }
//        if(obj instanceof City) {
////            activity.paintMenu(new CityMenu((City)obj, world, player));
//        }
//    }

}
