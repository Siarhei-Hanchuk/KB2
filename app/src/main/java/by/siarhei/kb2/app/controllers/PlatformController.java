package by.siarhei.kb2.app.controllers;

import by.siarhei.kb2.app.I18n;
import by.siarhei.kb2.app.Storage;
import by.siarhei.kb2.app.View;
import by.siarhei.kb2.app.ViewFactory;

public interface PlatformController {
    Storage getStorage();

    ViewFactory getViewFactory(GameOwner gameOwner);

    I18n getI18n();

    void setContentView(View view);

    void exit();
}
