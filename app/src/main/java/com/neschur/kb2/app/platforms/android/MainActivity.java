package com.neschur.kb2.app.platforms.android;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.neschur.kb2.app.I18n;
import com.neschur.kb2.app.controllers.ApplicationController;
import com.neschur.kb2.app.controllers.implementations.MainMenuControllerImpl;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        I18n.setResources(getResources());
        ApplicationController.initApp(new PlatformControllerImpl(this));
        new MainMenuControllerImpl();
    }

    @Override
    public void onBackPressed() {
        new MainMenuControllerImpl();
    }
}
