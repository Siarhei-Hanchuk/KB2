package by.siarhei.kb2.app.platforms.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import by.siarhei.kb2.app.controllers.ApplicationController;
import by.siarhei.kb2.app.controllers.implementations.MainMenuControllerImpl;

public class MainActivity extends Activity {
    private static MainActivity act;

    public static void showToast(CharSequence text) {
        Context context = act.getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ApplicationController.initApp(new PlatformControllerImpl(this));
        new MainMenuControllerImpl();
        act = this;
    }

    @Override
    public void onBackPressed() {
        new MainMenuControllerImpl();
    }
}
