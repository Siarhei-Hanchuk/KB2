package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.models.MapPoint;

import java.util.Random;

public class GoldChest extends Entity {
    private int wealth;
    private boolean bonus = false;

    public GoldChest(MapPoint point, int wealth) {
        super(point);
        this.wealth = wealth;
        int rand = (new Random()).nextInt(10);
        if (rand == 0)
            bonus = true;
    }

    @Override
    public int getID() {
        return R.drawable.goldchest;
    }

    public boolean isBonus() {
        return bonus;
    }

    public int getGold() {
        return (int) ((getMaxGold() - getMinGold()) * Math.random() + getMinGold()) * 10;
    }

    public int getAuthority() {
        return (int) ((getMaxGold() - getMinGold()) * Math.random() + getMinGold()) / 5;
    }

    public int getSalary() {
        return (wealth + 1) * 500;
    }

    private int getMinGold() {
        switch (wealth) {
            case 0:
                return 21;
            case 1:
                return 42;
            case 2:
                return 65;
            case 3:
                return 93;
            case 4:
                return 221;
        }
        return 0;
    }

    private int getMaxGold() {
        switch (wealth) {
            case 0:
                return 54;
            case 1:
                return 119;
            case 2:
                return 197;
            case 3:
                return 272;
            case 4:
                return 691;
        }
        return 0;
    }
}
