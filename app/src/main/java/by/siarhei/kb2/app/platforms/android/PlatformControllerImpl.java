package by.siarhei.kb2.app.platforms.android;

import android.app.Activity;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.Storage;
import by.siarhei.kb2.app.View;
import by.siarhei.kb2.app.ViewFactory;
import by.siarhei.kb2.app.controllers.GameOwner;
import by.siarhei.kb2.app.controllers.PlatformController;

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
        return null;
    }

    @Override
    public I18n getI18n() {
        return new I18nImpl(activity.getResources());
    }

    @Override
    public void setContentView(View view) {
        activity.setContentView((MainView) view);
    }

    @Override
    public void exit() {
        activity.finish();
        System.exit(0);
    }
}
