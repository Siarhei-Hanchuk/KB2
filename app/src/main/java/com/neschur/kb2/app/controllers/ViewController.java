package com.neschur.kb2.app.controllers;

import android.content.Context;

public interface ViewController extends GameOwner {
    public void viewClose();

    public Context getContext();
}
