package by.siarhei.kb2.app.server.saves;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import by.siarhei.kb2.app.server.entities.Entity;
import by.siarhei.kb2.app.server.models.Game;
import by.siarhei.kb2.app.server.warriors.Warrior;

public class Saver {
    public static String serialize(Game game) {
        return gson().toJson(game);
    }
    public static Game deserialize(String data) {
        return gson().fromJson(data, Game.class);
    }

    private static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Entity.class, new EntitySerializer());
        gsonBuilder.registerTypeAdapter(Warrior.class, new WarriorSerializer());
        return gsonBuilder.create();
    }
}
