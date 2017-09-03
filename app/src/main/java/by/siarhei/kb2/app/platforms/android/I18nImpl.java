package by.siarhei.kb2.app.platforms.android;

import android.content.res.Resources;

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
            return resources.getString(R.string.class.getField(key).getInt(new R.string()));
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
            result = resources.getString(R.string.class.getField(key).getInt(new R.string()));
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
