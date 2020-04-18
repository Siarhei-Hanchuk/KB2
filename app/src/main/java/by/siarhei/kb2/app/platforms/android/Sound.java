package by.siarhei.kb2.app.platforms.android;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.HashMap;

public class Sound {
    private static Context context;
    private static final HashMap<Integer, MediaPlayer> cache = new HashMap<>();

    public static void setContext(Context context) {
        Sound.context = context;
    }

    public static void play(int resourceId) {
        MediaPlayer player = cache.get(resourceId);
        if(player == null) {
            player = MediaPlayer.create(context, resourceId);
            cache.put(resourceId, player);
        }

        player.start();
    }
}
