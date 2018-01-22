package by.siarhei.kb2.app.controllers;

import by.siarhei.kb2.app.View;
import by.siarhei.kb2.app.ViewFactory;

public interface ViewController extends GameOwner {
    void viewClose();
    ViewFactory getViewFactory();
    void setContentView(View view);
}
