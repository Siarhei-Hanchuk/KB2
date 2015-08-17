package com.neschur.kb2.app.platforms.android.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.TrainingData;
import com.neschur.kb2.app.controllers.MainViewController;
import com.neschur.kb2.app.models.GameGrid;
import com.neschur.kb2.app.platforms.android.views.helpers.Click;
import com.neschur.kb2.app.platforms.android.views.helpers.Painter;

class MainView extends ViewImpl {
    private final MainViewController mainViewController;
    private boolean trainingMode = false;
    private int trainingStep = 0;

    public MainView(Context context, MainViewController mainViewController) {
        super(context, mainViewController);
        this.mainViewController = mainViewController;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if(trainingMode) {
            trainingMode = false;
            trainingStep++;
            refresh();
            super.onTouchEvent(event);
        }

        Click click = getClick(event);
        if (mainViewController.getGame() == null)
            return super.onTouchEvent(event);
        int x = click.getX();
        int y = click.getY();
        if (x > this.stepX() * 5) {
            int item = (y / stepY());
            mainViewController.touchMenu(item);
        } else {
            int height_2_5 = stepY() * 2;
            int height_3_5 = stepY() * 3;
            int width_2_5 = stepX() * 2;
            int width_3_5 = stepX() * 3;
            if (y > height_3_5) {
                if (x > width_2_5 && x < width_3_5) {
                    mainViewController.touchDown();
                }
                if (x < width_2_5) {
                    mainViewController.touchDownLeft();
                }
                if (x > width_3_5) {
                    mainViewController.touchDownRight();
                }
            }
            if (y < height_2_5) {
                if (x > width_2_5 && x < width_3_5) {
                    mainViewController.touchUp();
                }
                if (x < width_2_5) {
                    mainViewController.touchUpLeft();
                }
                if (x > width_3_5) {
                    mainViewController.touchUpRight();
                }
            }
            if (x > width_3_5 && y > height_2_5 && y < height_3_5) {
                mainViewController.touchRight();
            }
            if (x < width_2_5 && y > height_2_5 && y < height_3_5) {
                mainViewController.touchLeft();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        Painter painter = getPainter(canvas);
        canvas.drawColor(Color.BLACK);

        GameGrid grid = mainViewController.getGameGrid();

        for (int x = 0; x < GameGrid.STEP_X; x++) {
            for (int y = 0; y < GameGrid.STEP_Y; y++) {
                if (x < 5) {
                    painter.drawBitmap(getImageCache().getImage(grid.getBackgroundBuyXY(x, y)),
                            stepX() * x, stepY() * y);
                }
                if (grid.getImageBuyXY(x, y) > -1) {
                    painter.drawBitmap(getImageCache().getImage(grid.getImageBuyXY(x, y)),
                            stepX() * x, stepY() * y);
                }
            }
        }

        showTraining(canvas);
    }

    private void showTraining(@NonNull Canvas canvas) {
        if(!mainViewController.isTrainingMode()){
            return;
        }
        Painter painter = getPainter(canvas);
        TrainingData trainingData = mainViewController.getGame().getTrainingData();

        if(!trainingData.citiesDid()) {
            GameGrid grid = mainViewController.getGameGrid();
            for (int x = 0; x < GameGrid.STEP_X; x++) {
                for (int y = 0; y < GameGrid.STEP_Y; y++) {
                    if(grid.getImageBuyXY(x, y) == R.drawable.city) {
                        painter.drawTrainingCircle(x, y);
                    }
                }
            }

            Paint paint = new Paint(getDefaultPaint());
            paint.setColor(Color.GRAY);
            painter.drawRect(10, 10, 500, textHeight() * 2, paint);
            painter.drawText(i18n.translate(R.string.training_cities), 10, 10 + textHeight(), getDefaultPaint());

            trainingData.doneCities();
            trainingMode = true;
        }


        if(!trainingData.step2Did()) {
            painter.drawTrainingCircle(0, 0);
            painter.drawTrainingCircle(4, 4);
            painter.drawTrainingCircle(4, 0);
            painter.drawTrainingCircle(0, 4);

            Paint paint = new Paint(getDefaultPaint());
            paint.setColor(Color.GRAY);
            painter.drawRect(10, 10, 500, textHeight() * 2, paint);
            painter.drawText(i18n.translate(R.string.training_moving2), 10, 10 + textHeight(), getDefaultPaint());

            trainingData.doneStep2();
            trainingMode = true;
        }

        if(!trainingData.step1Did()) {
            painter.drawTrainingCircle(0, 2);
            painter.drawTrainingCircle(2, 0);
            painter.drawTrainingCircle(4, 2);
            painter.drawTrainingCircle(2, 4);

            Paint paint = new Paint(getDefaultPaint());
            paint.setColor(Color.GRAY);
            painter.drawRect(10, 10, 500, textHeight() * 2, paint);
            painter.drawText(i18n.translate(R.string.training_moving), 10, 10 + textHeight(), getDefaultPaint());

            trainingData.doneStep1();
            trainingMode = true;
        }
    }
}
