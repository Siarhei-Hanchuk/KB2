package com.neschur.kb2.app.ui;

import android.app.Activity;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GuidePost;
import com.neschur.kb2.app.entities.MapNext;

/**
 * Created by siarhei on 14.1.15.
 */
public class MessageFactory {
    private static Activity activity;

    public static void create(Activity activity) {
        MessageFactory.activity = activity;
    }

    public static String getMessage(Entity entity) {
        Integer id = null;
        if(entity instanceof MapNext)
            id = R.string.entity_mapNext_message;
//        if(entity instanceof GuidePost)
//            id = R.string.entity_mapNext;
        if(id != null)
            return activity.getResources().getString(id);
        return null;
    }
}
