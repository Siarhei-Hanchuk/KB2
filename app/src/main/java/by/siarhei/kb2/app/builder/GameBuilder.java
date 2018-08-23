package by.siarhei.kb2.app.builder;

import by.siarhei.kb2.app.countries.World;
import by.siarhei.kb2.app.models.Game;
import by.siarhei.kb2.app.models.Player;

import static by.siarhei.kb2.app.models.Game.MODE_GAME;

public class GameBuilder {
    public static Game build(int mode) {

        World world = new World(mode);
        Player player = new Player(world.getCountry(0), mode);
        int weeks = 999;
        if (mode == Game.MODE_GAME) {
            weeks = 200 - 1;
        } else if (mode == Game.MODE_TRAINING) {
            weeks = 600 - 1;
        }
        Game game = new Game(world, player, weeks);

        return game;
    }
}
