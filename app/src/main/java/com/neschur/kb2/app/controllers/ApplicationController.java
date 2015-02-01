package com.neschur.kb2.app.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.SurfaceView;

public abstract class ApplicationController implements ViewController{
    protected Activity activity;

    public ApplicationController(Activity activity) {
        this.activity = activity;
    }

    @Override
    public Context getContext() {
        return activity;
    }

    public void setContentView(SurfaceView view) {
        activity.setContentView(view);
    }
}
