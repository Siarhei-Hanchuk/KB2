package com.neschur.kb2.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.neschur.kb2.app.controllers.GameController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    private SharedPreferences prefs;

    public Storage(Activity activity) {
        prefs = activity.getSharedPreferences("main", Context.MODE_PRIVATE);
    }

    public boolean saveGame(GameController game, String key) {
        return saveObject(key, game);
    }

    public GameController loadGame(String key) {
        return (GameController) loadObject(key);
    }

    private boolean saveObject(String key, Object object) {
        try {
            ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
            ObjectOutputStream outStream = new ObjectOutputStream(outBytes);
            outStream.writeObject(object);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(key, Base64.encodeToString(outBytes.toByteArray(), Base64.DEFAULT));
            editor.apply();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Object loadObject(String key) {
        try {
            byte[] data = Base64.decode(prefs.getString(key, ""), Base64.DEFAULT);
            ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(data));
            return is.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
