package com.neschur.kb2.app.platforms;

import android.app.Activity;

import com.neschur.kb2.app.controllers.AppControllerImpl;
import com.neschur.kb2.app.views.View;

public class AndroidAppControllerImpl implements AppControllerImpl {
    private Activity activity;

    public AndroidAppControllerImpl(Activity _activity) {
        activity = _activity;
    }
    @Override
    public void setContentView(View view) {
        activity.setContentView(view);
    }

    @Override
    public void exit() {
        activity.finish();
        System.exit(0);
    }
}
