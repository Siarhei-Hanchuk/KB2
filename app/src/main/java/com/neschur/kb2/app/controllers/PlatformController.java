package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.Storage;
import com.neschur.kb2.app.ViewFactory;
import com.neschur.kb2.app.platforms.android.views.View;

public interface PlatformController {
    Storage getStorage();

    ViewFactory getViewFactory(GameOwner gameOwner);

    public I18n getI18n();

    public void setContentView(View view);

    public void exit();
}
