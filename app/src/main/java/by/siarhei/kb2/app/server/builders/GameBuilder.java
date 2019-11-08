package by.siarhei.kb2.app.server.builders;

import by.siarhei.kb2.app.server.countries.World;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.models.Player;

public class GameBuilder {
    public static Game build(int mode) {

        World world = WorldBuilder.build(mode);
        Player player = new Player(world.getCountry(0), mode);
        int weeks = 999;
        if (mode == Game.MODE_GAME) {
            weeks = 200 - 1;
        } else if (mode == Game.MODE_TRAINING) {
            weeks = 600 - 1;
        }

        return new Game(world, player, weeks);
    }
}
