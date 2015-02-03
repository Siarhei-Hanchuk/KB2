package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.controllers.PlayerViewsController;
import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.MapPoint;
import com.neschur.kb2.app.models.Player;

class MapView extends ViewImpl {
    private final Player player;

    public MapView(Context context, PlayerViewsController playerViewsController) {
        super(context, playerViewsController);
        this.player = playerViewsController.getPlayer();
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        viewController.viewClose();
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int pointSize = canvas.getHeight() / Country.MAX_MAP_SIZE;
        Country country = player.getCountry();
        boolean memory[][] = player.getMemory().getMap(country.getId());

        Paint paint = new Paint();
        for (int i = 0; i < Country.MAX_MAP_SIZE; i++) {
            for (int j = 0; j < Country.MAX_MAP_SIZE; j++) {
                if (!memory[i][j])
                    continue;

                setMapPointColor(paint, country.getMapPoint(i, j));

                canvas.drawRect(pointSize * i, pointSize * j,
                        pointSize * (i + 1), pointSize * (j + 1), paint);
            }
        }
    }

    private void setMapPointColor(Paint paint, MapPoint mapPoint) {
        if (mapPoint.getEntity() == null) {
            setMapPointColorForLand(paint, mapPoint.getLand());
        } else {
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
            case R.drawable.army_shop:
                paint.setColor(Color.GRAY);
                break;
            case R.drawable.captain:
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

