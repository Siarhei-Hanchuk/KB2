package by.siarhei.kb2.app.platforms.android.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import by.siarhei.kb2.app.R;
import by.siarhei.kb2.app.platforms.android.MainView;
import by.siarhei.kb2.app.server.Response;
import by.siarhei.kb2.app.server.countries.Country;
import by.siarhei.kb2.app.server.models.MapPoint;
import by.siarhei.kb2.app.server.models.Player;
import by.siarhei.kb2.app.platforms.android.helpers.Painter;

class MapView extends RootView {

    public MapView(MainView mainView) {
        super(mainView);
    }

    @Override
    public void draw(@NonNull Canvas canvas, Response response) {
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        Player player = response.getPlayer();
        int pointSize = canvas.getHeight() / Country.MAX_MAP_SIZE;
        Country country = player.getCountry();
        boolean[][] memory = player.getMemory().getMap(country.getId());

        Paint paint = new Paint();
        for (int i = 0; i < Country.MAX_MAP_SIZE; i++) {
            for (int j = 0; j < Country.MAX_MAP_SIZE; j++) {
                if (!memory[i][j])
                    continue;

                setMapPointColor(paint, country.getMapPoint(i, j));

                painter.drawRect(pointSize * i, pointSize * j,
                        pointSize * (i + 1), pointSize * (j + 1), paint);
            }
        }

        paint.setColor(Color.RED);
        painter.drawRect(pointSize * player.getX(), pointSize * player.getY(),
                pointSize * (player.getX() + 1), pointSize * (player.getY() + 1), paint);

        String xText = "X: " + player.getX();
        String yText = "Y: " + player.getY();
        int textLength = Math.max((int) getDefaultPaint().measureText(xText) + 1,
                (int) getDefaultPaint().measureText(yText) + 1);
        painter.drawText(xText, textLength, textHeight(),
                getDefaultPaint(), Painter.ALIGN_RIGHT);
        painter.drawText(yText, textLength, textHeight() * 2,
                getDefaultPaint(), Painter.ALIGN_RIGHT);
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
                paint.setColor(Color.MAGENTA);
                break;
            case R.drawable.city:
                paint.setColor(Color.MAGENTA);
                break;
            case R.drawable.castle_c:
            case R.drawable.castle_r:
            case R.drawable.castle_l:
                paint.setColor(Color.rgb(255, 140, 0));
                break;
            case R.drawable.guidepost:
                paint.setColor(Color.GRAY);
                break;
            case R.drawable.gold_chest:
                paint.setColor(Color.CYAN);
                break;
            case R.drawable.nave:
                paint.setColor(Color.WHITE);
                break;
            default:
                paint.setColor(Color.BLACK);
                break;
        }
    }
}

