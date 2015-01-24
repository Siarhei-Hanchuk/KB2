package com.neschur.kb2.app.warriors;

import com.neschur.kb2.app.R;

import java.util.Random;

public class WarriorFactory {
    private static String[] group0 = {"peasant", "aborigine", "boar", "woodgoblin", "skeleton"};
    private static String[] group1 = {"elf", "dwarf", "zombie", "rknight", "gorilla"};
    private static String[] group2 = {"cannibal", "ghost", "lion", "druid", "troll"};
    private static String[] group3 = {"elephant", "snake", "vampire", "giant", "knight"};
    private static String[] group4 = {"centaur", "dinosaur"};
    private static String[] group5 = {"daemon", "cyclops", "dragon"};

    public static Warrior createRandomFromGroup(int group) {
        switch (group) {
            case 0:
                return create(group0[(new Random()).nextInt(5)]);
            case 1:
                return create(group1[(new Random()).nextInt(5)]);
            case 2:
                return create(group2[(new Random()).nextInt(5)]);
            case 3:
                return create(group3[(new Random()).nextInt(5)]);
            case 4:
                return create(group4[(new Random()).nextInt(2)]);
            case 5:
                return create(group5[(new Random()).nextInt(3)]);
            default:
                return null;
        }
    }

    public static Warrior create(String id) {
        switch (id) {
            case "peasant":
                return new Warrior(id, R.drawable.army_peasant, 2, 1, 1, false, false, 1000, 50);
            case "aborigine":
                return new Warrior(id, R.drawable.army_aborigine, 5, 2, 1, false, true, 500, 60);
            case "boar":
                return new Warrior(id, R.drawable.army_boar, 7, 2, 2, false, false, 500, 75);
            case "skeleton":
                return new Warrior(id, R.drawable.army_skeleton, 8, 2, 2, false, false, 500, 85);
            case "woodgoblin":
                return new Warrior(id, R.drawable.army_woodgoblin, 8, 3, 1, true, false, 400, 100);
            case "elf":
                return new Warrior(id, R.drawable.army_elf, 18, 6, 2, false, true, 200, 200);
            case "dwarf":
                return new Warrior(id, R.drawable.army_dwarf, 20, 6, 1, false, false, 200, 200);
            case "gorilla":
                return new Warrior(id, R.drawable.army_gorilla, 20, 6, 2, false, false, 200, 200);
            case "zombie":
                return new Warrior(id, R.drawable.army_zombie, 22, 6, 1, false, false, 200, 250);
            case "rknight":
                return new Warrior(id, R.drawable.army_rknight, 25, 8, 1, false, false, 200, 300);
            case "druid":
                return new Warrior(id, R.drawable.army_druid, 60, 22, 3, false, true, 100, 500);
            case "cannibal":
                return new Warrior(id, R.drawable.army_cannibal, 60, 30, 2, false, false, 100, 700);
            case "ghost":
                return new Warrior(id, R.drawable.army_ghost, 70, 15, 3, false, false, 100, 600);
            case "lion":
                return new Warrior(id, R.drawable.army_lion, 70, 22, 4, false, false, 100, 800);
            case "snake":
                return new Warrior(id, R.drawable.army_snake, 70, 65, 1, false, false, 30, 1500);
            case "troll":
                return new Warrior(id, R.drawable.army_troll, 100, 25, 1, false, false, 100, 1000);
            case "vampire":
                return new Warrior(id, R.drawable.army_vampire, 110, 40, 1, true, false, 50, 2000);
            case "giant":
                return new Warrior(id, R.drawable.army_giant, 120, 45, 2, false, true, 50, 3000);
            case "elephant":
                return new Warrior(id, R.drawable.army_elephant, 130, 50, 1, false, false, 50, 2000);
            case "knight":
                return new Warrior(id, R.drawable.army_knight, 150, 55, 1, false, false, 70, 3000);
            case "centaur":
                return new Warrior(id, R.drawable.army_centaur, 400, 200, 3, false, true, 30, 5000);
            case "dinosaur":
                return new Warrior(id, R.drawable.army_dinosaur, 450, 200, 2, false, false, 50, 5000);
            case "daemon":
                return new Warrior(id, R.drawable.army_daemon, 500, 200, 1, true, false, 30, 7000);
            case "cyclops":
                return new Warrior(id, R.drawable.army_cyclops, 600, 400, 2, false, false, 30, 8000);
            case "dragon":
                return new Warrior(id, R.drawable.army_dragon, 1000, 250, 1, true, false, 30, 10000);
            default:
                return null;
        }
    }
}
