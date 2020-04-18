package by.siarhei.kb2.app.platforms.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import by.siarhei.kb2.app.Storage;

public class StorageImpl implements Storage {
    private final SharedPreferences prefs;

    public StorageImpl(Activity activity) {
        prefs = activity.getSharedPreferences("main", Context.MODE_PRIVATE);
    }

    @Override
    public void saveGame(String data, String key) {

        saveObject(key, data);
    }

    @Override
    public String loadGame(String key) {
        return (String) loadObject(key);
    }

    private void saveObject(String key, String string) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, string);
        editor.apply();
    }

    private Object loadObject(String key) {
        return prefs.getString(key, null);
    }
}
