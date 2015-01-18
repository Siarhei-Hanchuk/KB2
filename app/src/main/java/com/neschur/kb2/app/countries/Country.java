package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.ArmyShop;
import com.neschur.kb2.app.entities.Captain;
import com.neschur.kb2.app.entities.Castle;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.GuidePost;
import com.neschur.kb2.app.entities.Magician;
import com.neschur.kb2.app.entities.MapNext;
import com.neschur.kb2.app.entities.Sorcerer;
import com.neschur.kb2.app.models.MapPoint;

import java.util.Random;

/**
 * Created by siarhei on 2.6.14.
 */
public abstract class Country {
    public final static int MAX_MAP_SIZE = 65;
    protected MapPoint[][] map;
    protected int id;
    protected Random random;
    private Sorcerer sorcerer;

    public Country(int id) {
        this.id = id;
        this.random = new Random();

        map = new MapPoint[65][65];
        for (int i = 0; i < 65; i++) {
            for (int j = 0; j < 65; j++) {
                map[i][j] = new MapPoint();
            }
        }

        base();
//        createSorcerer(5, 7);//debug
    }

    public void createWizards() {
        // TODO
    }

    private void createSorcerer(int x, int y){
        sorcerer = new Sorcerer(this, x, y);
    }

    public Sorcerer getSorcerer() {
        return sorcerer;
    }

    protected int rand(int n) {
        return random.nextInt(n);
    }

    public MapPoint getMapPoint(int x, int y) {
        return map[x][y];
    }

    public int getId() {
        return id;
    }

    private void base() {
        for (int i = 0; i < MAX_MAP_SIZE; i++) {
            for (int j = 0; j < MAX_MAP_SIZE; j++) {
                map[i][j].setLand(R.drawable.water);
            }
        }
        for (int i = 5; i < MAX_MAP_SIZE - 5; i++) {
            for (int j = 5; j < MAX_MAP_SIZE - 5; j++) {
                map[i][j].setLand(R.drawable.land);
            }
        }
    }

    protected void cities() {
        int count = 1;
        int x;
        int y;
        while (count < 5) {
            x = rand(MAX_MAP_SIZE - 1);
            y = rand(MAX_MAP_SIZE - 1);

            if (((map[x + 1][y].getLand() == R.drawable.water) || (map[x - 1][y].getLand() == R.drawable.water)
                    || (map[x][y + 1].getLand() == R.drawable.water) || (map[x][y - 1].getLand() == R.drawable.water)) &&
                    ((map[x][y].getLand() == R.drawable.land) && (map[x][y].getEntity() == null))) {
                map[x][y].setEntity(new City(this, x, y));
                count++;
            }
        }
    }

    protected void guidePosts() {
        int count = 0;
        while (count < 5) {
            int y = rand(54) + 5;
            int x = rand(54) + 5;
            if ((map[x][y].land == R.drawable.land) || (map[x][y].land == R.drawable.sand)) {
                map[x][y].setEntity(new GuidePost(this, x, y));
            }
            count++;
        }
    }

    protected void goldChests(int frequency, int min, int max) {
        for (int i = 5; i < MAX_MAP_SIZE - 5; i++) {
            for (int j = 5; j < MAX_MAP_SIZE - 5; j++) {
                if (((map[i][j].land == R.drawable.land) || (map[i][j].land == R.drawable.sand)) && (map[i][j].getEntity() == null)) {
                    if (rand(frequency) == 1) {
                        map[i][j].setEntity(new GoldChest(this, i, j, min, max));
                    }
                }
            }
        }
    }

    private void createRiver(int x, int y, int direction, int length, int type) {
        int run = 0;
        int width;
        while (run < length) {
            run++;
            width = rand(3);
            for (int coordinate = -width - 1; coordinate < +width + 1; coordinate++) {
                if (type == 1) {
                    map[x][y + coordinate].land = R.drawable.water;
                } else {
                    map[x + coordinate][y].land = R.drawable.water;
                }
            }
            int center;
            int route;
            if (type == 1) {
                center = y;
                route = x;
            } else {
                center = x;
                route = y;
            }
            center = center + rand(5) - 2;
            if (center > 62)
                center = 62;
            if (center < 3)
                center = 3;
            route += direction;
            if (type == 1) {
                y = center;
                x = route;
            } else {
                x = center;
                y = route;
            }
        }
    }

    protected void river(int length) {
        int start1 = rand(53) + 5;
        int r = rand(4);
        switch (r) {
            case 0:
                createRiver(5, start1, 1, length, 1);
                break;
            case 1:
                createRiver(59, start1, -1, length, 1);
                break;
            case 2:
                createRiver(start1, 5, 1, length, 2);
                break;
            case 3:
                createRiver(start1, 59, -1, length, 2);
                break;
            default:
                break;
        }
    }

    protected void landscape(int frequency, int land) {
        for (int i = 5; i < MAX_MAP_SIZE - 5; i++) {
            for (int j = 5; j < MAX_MAP_SIZE - 5; j++) {
                if (rand(frequency) == 1) {
                    if ((map[i][j].land == R.drawable.land) && (map[i][j].getEntity() == null)) {
                        map[i][j].land = land;
                    }
                }
            }
        }
    }

    protected void mapNext() {
        int x;
        int y;
        do {
            y = rand(54) + 5;
            x = rand(54) + 5;
        } while (map[x][y].land != R.drawable.land && map[x][y].getEntity() == null);
        map[x][y].setEntity(new MapNext(this, x, y));
    }

    private boolean tryPlaceCastle(int x, int y) {
        if (((map[x][y].land == R.drawable.land) &&
                (map[x - 1][y].land == R.drawable.land) &&
                (map[x + 1][y].land == R.drawable.land) &&
                (map[x][y + 1].land == R.drawable.land)) &&
                ((map[x][y].getEntity() == null) &&
                        (map[x - 1][y].getEntity() == null) &&
                        (map[x + 1][y].getEntity() == null) &&
                        (map[x][y + 1].getEntity() == null))) {
            map[x][y].setEntity(new Castle(this, x, y, R.drawable.castle_c));
            map[x + 1][y].setEntity(new Castle(this, x, y, R.drawable.castle_r));
            map[x - 1][y].setEntity(new Castle(this, x, y, R.drawable.castle_l));
            map[x][y + 1].setEntity(new Captain(this, x, y));
            return true;
        }
        return false;
    }

    private void castles() {
        int count = 0;
        while (count < 5) {
            if (tryPlaceCastle(rand(MAX_MAP_SIZE), rand(MAX_MAP_SIZE))) {
                count++;
            }
        }
    }

    private boolean tryPlaceCaptain(int x, int y) {
        if (map[x][y].land == R.drawable.land && map[x][y].getEntity() == null) {
            map[x][y].setEntity(new Captain(this, x, y));
            return true;
        }
        return false;
    }

    private void captains() {
        int count = 5;
        while (count < 20) {
            if (tryPlaceCaptain(rand(MAX_MAP_SIZE), rand(MAX_MAP_SIZE)))
                count++;
        }
    }

    public boolean worker(int n, int x, int y) {
        int oldType = 0;
        int newType = 0;
        switch (n) {
            case 0:
                oldType = R.drawable.water;
                newType = R.drawable.plot;
                break;
            case 1:
                oldType = R.drawable.forest;
                newType = R.drawable.land;
                break;
            case 2:
                oldType = R.drawable.land;
                newType = R.drawable.water;
                break;
            case 3:
                oldType = R.drawable.stone;
                newType = R.drawable.land;
                break;
            default:
                return false;
        }
        if (map[x][y].land == oldType) {
            map[x][y].land = newType;
            return true;
        }
        return false;
    }

    void army(int count, int group) {
        int run = 0;
        while(run < count){
            int x = rand(65);
            int y = rand(65);
            MapPoint mp = getMapPoint(x, y);
            if (mp.getEntity() == null && mp.getLand() == R.drawable.land){
                mp.setEntity(new ArmyShop(this, x, y, group));
                run++;
            }
        }
    }
}

