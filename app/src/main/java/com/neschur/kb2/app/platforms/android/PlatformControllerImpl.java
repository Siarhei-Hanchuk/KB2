package com.neschur.kb2.app.platforms.android;

import android.app.Activity;

import com.neschur.kb2.app.Storage;
import com.neschur.kb2.app.controllers.GameOwner;
import com.neschur.kb2.app.controllers.PlatformController;
import com.neschur.kb2.app.views.View;
import com.neschur.kb2.app.views.ViewFactory;

public class PlatformControllerImpl implements PlatformController {
    private Activity activity;

    public PlatformControllerImpl(Activity _activity) {
        activity = _activity;
    }

    @Override
    public Storage getStorage() {
        return new StorageImpl(activity);
    }

    @Override
    public ViewFactory getViewFactory(GameOwner gameOwner) {
        return new ViewFactory(activity, gameOwner);
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
