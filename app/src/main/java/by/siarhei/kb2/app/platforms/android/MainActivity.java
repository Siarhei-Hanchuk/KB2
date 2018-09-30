package by.siarhei.kb2.app.platforms.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import by.siarhei.kb2.app.controllers.ApplicationController;
import by.siarhei.kb2.app.controllers.PlatformController;
import by.siarhei.kb2.app.server.Server;

public class MainActivity extends Activity {
    private static MainActivity activity;
    private MainView view;

    public static void showToast(CharSequence text) {
        Context context = activity.getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public static Activity getActivity() {
        return activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        PlatformController platformController = new PlatformControllerImpl(this);
        ApplicationController.initApp(platformController);
        Server.setI18n(ApplicationController.i18n);
        view = new MainView(this);
        platformController.setContentView(view);
        activity = this;
    }

    @Override
    public void onBackPressed() {
        view.switchOnMenu();
    }
}
