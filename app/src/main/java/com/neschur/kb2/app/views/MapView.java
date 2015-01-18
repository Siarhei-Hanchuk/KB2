package com.neschur.kb2.app.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.MainController;
import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.models.MapPoint;

/**
 * Created by siarhei on 18.1.15.
 */
public class MapView extends SurfaceView implements SurfaceHolder.Callback, Drawable {
    private MainController mainController;
    private DrawThread drawThread;

    public MapView(Context context, MainController mainController) {
        super(context);
        getHolder().addCallback(this);
        this.mainController = mainController;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder(), this);
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mainController.closeMenu();
        drawThread.refresh();
        return super.onTouchEvent(event);
    }

    public void draw(Canvas canvas) {
        int pointSize = canvas.getHeight()/ Country.MAX_MAP_SIZE;
        Country country = mainController.getGameController().getPlayer().getCountry();
        Paint paint = new Paint();
        for (int i = 0; i < Country.MAX_MAP_SIZE; i++) {
            for (int j = 0; j < Country.MAX_MAP_SIZE; j++) {
                setMapPointColor(paint, country.getMapPoint(i, j));

                canvas.drawRect(pointSize * i, pointSize * j,
                        pointSize * (i + 1), pointSize * (j + 1), paint);
            }
        }
    }

    private void setMapPointColor(Paint paint, MapPoint mapPoint) {
        if( mapPoint.getEntity() == null ){
            setMapPointColorForLand(paint, mapPoint.getLand());
        }else{
            setMapPointColorForEntity(paint, mapPoint.getEntity().getID());
        }
    }

    private void setMapPointColorForLand(Paint paint, int land) {
        switch (land) {
            case R.drawable.water:
                paint.setColor(Color.BLUE);
                break;
            case R.drawable.land:
                paint.setColor(Color.GREEN);
                break;
            case R.drawable.forest:
                paint.setColor(Color.rgb(0, 77, 0));
                break;
            case R.drawable.stone:
                paint.setColor(Color.rgb(130, 51, 3));
                break;
            case R.drawable.sand:
                paint.setColor(Color.YELLOW);
                break;
            case R.drawable.plot:
                paint.setColor(Color.WHITE);
                break;
            default:
                paint.setColor(Color.BLACK);
                break;
        }
    }

    private void setMapPointColorForEntity(Paint paint, int entityId) {
        switch (entityId) {
            case R.drawable.army:
                paint.setColor(Color.GRAY);
                break;
            case R.drawable.capitan:
                paint.setColor(Color.CYAN);
                break;
            case R.drawable.city:
                paint.setColor(Color.GRAY);
                break;
            case R.drawable.castle_c:
            case R.drawable.castle_r:
            case R.drawable.castle_l:
                paint.setColor(Color.rgb(255, 140, 0));
                break;
            case R.drawable.guidepost:
                paint.setColor(Color.GRAY);
                break;
            case R.drawable.goldchest:
                paint.setColor(Color.CYAN);
                break;
            default:
                paint.setColor(Color.BLACK);
                break;
        }
    }
}

