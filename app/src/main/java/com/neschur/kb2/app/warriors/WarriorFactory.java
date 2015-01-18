package com.neschur.kb2.app.warriors;

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
                return new Warrior(id, 2, 1, 1, false, false, 1000, 50);
            case "aborigine":
                return new Warrior(id, 5, 2, 1, false, true, 500, 60);
            case "boar":
                return new Warrior(id, 7, 2, 2, false, false, 500, 75);
            case "skeleton":
                return new Warrior(id, 8, 2, 2, false, false, 500, 85);
            case "woodgoblin":
                return new Warrior(id, 8, 3, 1, true, false, 400, 100);
            case "elf":
                return new Warrior(id, 18, 6, 2, false, true, 200, 200);
            case "dwarf":
                return new Warrior(id, 20, 6, 1, false, false, 200, 200);
            case "zombie":
                return new Warrior(id, 22, 6, 1, false, false, 200, 250);
            case "rknight":
                return new Warrior(id, 25, 8, 1, false, false, 200, 300);
            case "gorilla":// TODO
                return new Warrior(id, 20, 8, 1, false, false, 111, 111);
            case "cannibal":
                return new Warrior(id, 5, 2, 1, false, false, 0, 0);
            case "ghost":
                return new Warrior(id, 5, 2, 1, false, false, 0, 0);
            case "lion":
                return new Warrior(id, 5, 2, 1, false, false, 0, 0);
            case "druid":
                return new Warrior(id, 5, 2, 1, false, false, 0, 0);
            case "troll":
                return new Warrior(id, 5, 2, 1, false, false, 0, 0);
            case "elephant":
                return new Warrior(id, 5, 2, 1, false, false, 0, 0);
            case "snake":
                return new Warrior(id, 70, 65, 1, false, false, 30, 1500);
            case "vampire":
                return new Warrior(id, 110, 40, 1, true, false, 50, 2000);
            case "giant":
                return new Warrior(id, 120, 45, 2, false, true, 50, 3000);
            case "knight":
                return new Warrior(id, 5, 2, 1, false, false, 0, 0);
            case "centaur":
                return new Warrior(id, 5, 2, 1, false, false, 0, 0);
            case "dinosaur":
                return new Warrior(id, 450, 200, 2, false, false, 50, 5000);
            case "daemon":
                return new Warrior(id, 5, 2, 1, false, false, 0, 0);
            case "cyclops":
                return new Warrior(id, 5, 2, 1, false, false, 0, 0);
            case "dragon":
                return new Warrior(id, 1000, 250, 1, true, false, 30, 10000);
            default:
                return null;
        }
    }
}
