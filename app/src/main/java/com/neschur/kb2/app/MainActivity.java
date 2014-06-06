package com.neschur.kb2.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.neschur.kb2.app.controllers.MainController;


public class MainActivity extends Activity {
    private ImageView[][] images = new ImageView[6][5];
    MainController controller = new MainController(this);

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
                images[x][y].setLayoutParams(lpView);
                linLayout.addView(images[x][y]);
            }
        }

        images[1][2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.move(-1,0);
            }
        });

        images[3][2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.move(+1,0);
            }
        });

        images[2][1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.move(0,-1);
            }
        });

        images[2][3].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.move(0,+1);
            }
        });

        controller.start();
    }

    public void paint(int[][] ids){
        for(int x=0;x<6; x++) {
            for (int y = 0; y < 5; y++) {
                images[x][y].setImageResource(ids[x][y]);
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
