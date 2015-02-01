package com.neschur.kb2.app.views.interfaces;

import android.content.Context;

import com.neschur.kb2.app.models.GameGrid;

public interface ViewController extends ViewClosable, MainViewTouchReceiver {
    public Context getContext();
    public GameGrid getGameGrid();
}
