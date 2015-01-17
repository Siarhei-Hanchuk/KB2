package com.neschur.kb2.app;

import android.content.res.Resources;

/**
 * Created by siarhei on 18.1.15.
 */
public class I18n {
    private static Resources resources;

    public static void create(Resources resources) {
        I18n.resources = resources;
    }

    public static String translate(String key) {
        try {
            return resources.getString(R.string.class.getField(key)
                    .getInt(new R.string()));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return key;
    }
}
