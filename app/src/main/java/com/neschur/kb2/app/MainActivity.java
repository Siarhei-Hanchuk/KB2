package com.neschur.kb2.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {
    private ImageView[][] images = new ImageView[6][5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout linLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams linLayoutParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        setContentView(linLayout, linLayoutParam);

        for(int x=0;x<6; x++) {
            for (int y = 0; y < 5; y++) {
                RelativeLayout.LayoutParams lpView = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                lpView.leftMargin = 160 * x;
                lpView.topMargin = 120 * y;
                images[x][y] = new ImageView(this);
                images[x][y].setImageResource(R.drawable.land);
                images[x][y].setLayoutParams(lpView);
                linLayout.addView(images[x][y]);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
