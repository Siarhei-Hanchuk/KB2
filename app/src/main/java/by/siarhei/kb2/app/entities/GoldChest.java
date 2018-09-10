package by.siarhei.kb2.app.entities;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.models.MapPoint;

import java.util.Random;

public class GoldChest extends EntityImpl {
    private final int wealth;
    private int bonus = 0;
    private int gold;

    public GoldChest(MapPoint point, int wealth) {
        super();
        this.wealth = wealth;
    }

    @Override
    public int getID() {
        return R.drawable.gold_chest;
    }

    public boolean isBonus() {
        if (bonus == 0) {
            if ((new Random()).nextInt(10) == 0)
                bonus = 1;
            else
                bonus = 2;
        }
        return bonus == 1;
    }

    public int getGold() {
        if (gold > 0)
            return gold;
        gold = (int) ((getMaxGold() - getMinGold()) * Math.random() + getMinGold()) * 10;
        return gold;
    }

    public int getAuthority() {
        if (gold > 0)
            return gold / 50;
        gold = (int) ((getMaxGold() - getMinGold()) * Math.random() + getMinGold()) / 5;
        return gold / 50;
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
