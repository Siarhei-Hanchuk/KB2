package com.neschur.kb2.app.warriors;

public class WarriorFactory {
    static Warrior create(String id) {
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
