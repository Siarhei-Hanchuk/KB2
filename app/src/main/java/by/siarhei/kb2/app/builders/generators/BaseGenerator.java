package by.siarhei.kb2.app.builders.generators;

import java.io.Serializable;
import java.util.Random;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.countries.Country;
import by.siarhei.kb2.app.models.MapPoint;

public class BaseGenerator implements Serializable {
    private final Random random;
    private final MapPoint[][] map;

    public BaseGenerator(MapPoint[][] map) {
        this.random = new Random();
        this.map = map;
    }

    public void base() {
        for (int i = 0; i < Country.MAX_MAP_SIZE; i++) {
            for (int j = 0; j < Country.MAX_MAP_SIZE; j++) {
                map[i][j].setLand(R.drawable.water);
            }
        }
        for (int i = 5; i < Country.MAX_MAP_SIZE - 5; i++) {
            for (int j = 5; j < Country.MAX_MAP_SIZE - 5; j++) {
                map[i][j].setLand(R.drawable.land);
            }
        }
    }

    public void river(int length) {
        int start1 = random.nextInt(53) + 5;
        int r = random.nextInt(4);
        switch (r) {
            case 0:
                generateParamRiver(5, start1, 1, length, 1);
                break;
            case 1:
                generateParamRiver(59, start1, -1, length, 1);
                break;
            case 2:
                generateParamRiver(start1, 5, 1, length, 2);
                break;
            case 3:
                generateParamRiver(start1, 59, -1, length, 2);
                break;
            default:
                break;
        }
    }

    private void generateParamRiver(int x, int y, int direction, int length, int type) {
        int run = 0;
        int width;
        while (run < length) {
            run++;
            width = random.nextInt(3);
            for (int coordinate = -width - 1; coordinate < +width + 1; coordinate++) {
                if (type == 1) {
                    map[x][y + coordinate].setLand(R.drawable.water);
                } else {
                    map[x + coordinate][y].setLand(R.drawable.water);
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
            center = center + random.nextInt(5) - 2;
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

    public void landscape(double frequency, int land) {
        for (int i = 5; i < Country.MAX_MAP_SIZE - 5; i++) {
            for (int j = 5; j < Country.MAX_MAP_SIZE - 5; j++) {
                if (Math.random() < frequency) {
                    if ((map[i][j].getLand() == R.drawable.land)
                            && (map[i][j].getEntity() == null)) {
                        map[i][j].setLand(land);
                    }
                }
            }
        }
    }
}
