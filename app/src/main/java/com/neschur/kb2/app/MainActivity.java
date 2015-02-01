package com.neschur.kb2.app;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.neschur.kb2.app.controllers.MainMenuControllerImpl;

public class MainActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        I18n.setResources(getResources());

        new MainMenuControllerImpl(this);
    }

    @Override
    public void onBackPressed() {
        new MainMenuControllerImpl(this);
    }
}
