package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.City;
import com.neschur.kb2.app.entities.GoldChest;
import com.neschur.kb2.app.entities.GuidePost;
import com.neschur.kb2.app.entities.Magician;
import com.neschur.kb2.app.entities.MapNext;
import com.neschur.kb2.app.models.MapPoint;

import java.util.Random;

/**
 * Created by siarhei on 2.6.14.
 */
public abstract class Country {
    public final static int MAX_MAP_SIZE = 65;
    protected MapPoint[][] map;

    protected int rand(int n) {
        return Math.abs(new Random().nextInt()) % n;
    }

    public MapPoint getMapPoint(int x, int y) {
        return map[x][y];
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

//    public boolean worker(int n, int x, int y) {
//        int T1 = 0;
//        int T2 = 0;
//        switch (n) {
//            case 2:
//                T1 = R.drawable.forest;
//                T2 = R.drawable.land;
//                break;
//            case 4:
//                T1 = R.drawable.stone;
//                T2 = R.drawable.land;
//                break;
//            case 3:
//                T1 = R.drawable.land;
//                T2 = R.drawable.water;
//                break;
//            case 1:
//                T1 = R.drawable.water;
//                T2 = R.drawable.plot;
//                break;
//            default:
//                break;
//        }
//        if (map[x][y].land == T1) {
//            map[x][y].land = T2;
//            return true;
//        }
//        return false;
//    }

    protected void cities() {
        map[6][5].setEntity(new City(this, 6, 5));
        int count = 2; // 1 default for debug
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

    protected abstract int goldChestMin();

    protected abstract int goldChestMax();

    protected void goldChests(int frequency) {
        for (int i = 5; i < MAX_MAP_SIZE - 5; i++) {
            for (int j = 5; j < MAX_MAP_SIZE - 5; j++) {
                if (((map[i][j].land == R.drawable.land) || (map[i][j].land == R.drawable.sand)) && (map[i][j].getEntity() == null)) {
                    ;
                    if (rand(frequency) == 1) {
                        map[i][j].setEntity(new GoldChest(this, i, j, goldChestMin(), goldChestMax()));
                    }
                }
            }
        }
    }

	/*private void castels(){
        int x=0;
		int y=0;
		int c=0;
		while(c<5){
			x=rand(MAX_MAP_SIZE);
			y=rand(MAX_MAP_SIZE);
			if(((map[x][y].land==R.drawable.land)&&(map[x-1][y].land==R.drawable.land)&&(map[x+1][y].land==R.drawable.land)&&(map[x][y+1].land==R.drawable.land))&&
					((map[x][y].obj==0)&&(map[x-1][y].obj==0)&&(map[x+1][y].obj==0)&&(map[x][y+1].obj==0))){
				map[x][y].obj=R.drawable.castel_c;
				map[x+1][y].obj=R.drawable.castel_r;
				map[x-1][y].obj=R.drawable.castel_l;
				map[x][y].addid=c;
				map[x][y+1].obj=R.drawable.capitan;
				map[x][y+1].addid=c;
				capitan(c);
				c++;
			}
		}
	}*/

    //private void capitan(int cid){}

	/*@SuppressWarnings("unused")
    private void capitans(){
		int x;
		int y;
		int c=5;
		while(c<20){
			x=rand(56)+4;
			y=rand(56)+4;
			if(map[x][y].land==R.drawable.land && map[x][y].obj==0){
				map[x][y].obj=R.drawable.capitan;
				capitan(c);
				c++;
			}
		}
	}*/

    private void createRiver(int x, int y, int direction, int length, int type) {
        int p = 0;
        int q = 0;
        int w = 0;
        int o1 = 0;
        int o2 = 0;
        while (p < length) {
            p++;
            q = rand(2);
            q = rand(3);
            for (int o = -q - 1; o < w + 2; o++) {
                if (type == 1) {
                    o2 = o;
                } else {
                    o1 = o;
                }
                map[x + o1][y + o2].land = R.drawable.water;
            }
            int e;
            int r;
            if (type == 1) {
                e = y;
                r = x;
            } else {
                e = x;
                r = y;
            }
            e = e + rand(5) - 2;
            if (e > 62)
                e = 62;
            if (e < 3)
                e = 3;
            r += direction;
            if (type == 1) {
                y = e;
                x = r;
            } else {
                x = e;
                y = r;
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

    public Country() {
        map = new MapPoint[65][65];
        for (int i = 0; i < 65; i++) {
            for (int j = 0; j < 65; j++) {
                map[i][j] = new MapPoint();
            }
        }

        base();
        guidePosts();
        cities();
        new Magician(this, 5, 8); //debug
    }
}

