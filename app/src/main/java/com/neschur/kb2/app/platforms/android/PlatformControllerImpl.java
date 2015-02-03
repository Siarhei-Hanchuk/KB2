package com.neschur.kb2.app.platforms.android;

import android.app.Activity;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.Storage;
import com.neschur.kb2.app.View;
import com.neschur.kb2.app.ViewFactory;
import com.neschur.kb2.app.controllers.GameOwner;
import com.neschur.kb2.app.controllers.PlatformController;
import com.neschur.kb2.app.platforms.android.views.ViewImpl;
import com.neschur.kb2.app.platforms.android.views.ViewFactoryImpl;

class PlatformControllerImpl implements PlatformController {
    private final Activity activity;

    PlatformControllerImpl(Activity _activity) {
        activity = _activity;
    }

    @Override
    public Storage getStorage() {
        return new StorageImpl(activity);
    }

    @Override
    public ViewFactory getViewFactory(GameOwner gameOwner) {
        return new ViewFactoryImpl(activity, gameOwner);
    }

    @Override
    public I18n getI18n() {
        return new I18nImpl(activity.getResources());
    }

    @Override
    public void setContentView(View view) {
        activity.setContentView((ViewImpl)view);
    }

    @Override
    public void exit() {
        activity.finish();
        System.exit(0);
    }
}
