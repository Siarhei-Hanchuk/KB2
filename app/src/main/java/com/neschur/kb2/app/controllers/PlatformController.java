package com.neschur.kb2.app.controllers;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.Storage;
import com.neschur.kb2.app.View;
import com.neschur.kb2.app.ViewFactory;

public interface PlatformController {
    Storage getStorage();

    ViewFactory getViewFactory(GameOwner gameOwner);

    I18n getI18n();

    void setContentView(View view);

    void exit();
}
