package com.neschur.kb2.app;

import android.content.res.Resources;

public class I18n {
    private static Resources resources;

    public static void setResources(Resources resources) {
        I18n.resources = resources;
    }

    public static String translate(String key) {
        try {
            return resources.getString(R.string.class.getField(key).getInt(new R.string()));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static String translate(int key) {
        return resources.getString(key);
    }
}
