package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.World;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.Nave;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.Player;

/**
 * Created by siarhei on 11.1.15.
 */
public class GameController {
    private World world;
    private Player player;
    private Nave nave;
    private MainController mainController;

    public GameController(MainController mainController) {
        this.mainController = mainController;
        world = new World();
        player = new Player();
        player.setCountry(world.getCountry(0));
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public void move(int dx, int dy) {
        int x = player.getX();
        int y = player.getY();
        if (x + dx < 2 || x + dx > 62 || y + dy < 2 || y + dy > 62) {
            return;
        }

        MapPoint mp = player.getCountry().getMapPoint(x + dx, y + dy);

        if (mp.getEntity() == null) {
            if (player.inNave()) {
                if (mp.land == R.drawable.land || mp.land == R.drawable.sand) {
                    player.setNave(null);
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
            actionWithObject(player, mp.getEntity());
        }
    }

    private void actionWithObject(Player player, Entity entity) {
        if (entity instanceof Nave) {
            player.setNave((Nave) entity);
            player.move(((Nave) entity).getX(), ((Nave) entity).getY());
        } else {
            mainController.activateEntity(entity);
        }
    }

    public boolean getNave() {
        return nave != null;
    }

    public void createNave(int x, int y) {
        nave = new Nave(world.getCountry(0), x, y);
    }

    public void destroyNave() {
        nave.destroy();
        nave = null;
    }
}
