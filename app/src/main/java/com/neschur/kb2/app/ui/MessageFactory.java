package com.neschur.kb2.app.ui;

import android.app.Activity;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.GameController;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.GuidePost;
import com.neschur.kb2.app.entities.HarmfulMap;
import com.neschur.kb2.app.entities.MapNext;

/**
 * Created by siarhei on 14.1.15.
 */
public class MessageFactory {
    private static Activity activity;
    private static GameController gameController;

    public static void create(Activity activity, GameController gameController) {
        MessageFactory.activity = activity;
        MessageFactory.gameController = gameController;
    }

    public static Message getMessage(Entity entity) {
        if(entity instanceof MapNext)
            return new MapNextMessage(activity.getResources(), gameController);
        if(entity instanceof HarmfulMap)
            return new HarmfulMapMessage(activity.getResources(), gameController);
        return null;
    }
}
