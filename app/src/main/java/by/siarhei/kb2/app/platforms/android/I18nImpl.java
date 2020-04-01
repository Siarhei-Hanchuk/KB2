package by.siarhei.kb2.app.platforms.android;

import android.content.res.Resources;

import java.lang.reflect.Field;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.R;

class I18nImpl implements I18n {
    private final Resources resources;

    I18nImpl(Resources resources) {
        this.resources = resources;
    }

    @Override
    public String translate(String key) {
        try {
            Field field = R.string.class.getField(key);
            return resources.getString(field.getInt(field));
        } catch (NoSuchFieldException e) {
            return key;
        } catch (IllegalAccessException e) {
            return key;
        }
    }

    @Override
    public String translate(int key) {
        return resources.getString(key);
    }

    @Override
    public String translate(String key, String... replaces) {
        String result;
        try {
            Field field = R.string.class.getField(key);
            result = resources.getString(field.getInt(field));
        } catch (NoSuchFieldException e) {
            return key;
        } catch (IllegalAccessException e) {
            return key;
        }
        for (String item : replaces) {
            result = result.replace("#{?}", item);
        }
        return result;
    }

    @Override
    public String translate(String key, int... replaces) {
        String[] sReplaces = new String[replaces.length];
        int i = 0;
        for (int n : replaces) {
            sReplaces[i] = Integer.toString(n);
            i++;
        }
        return translate(key, sReplaces);
    }
}
