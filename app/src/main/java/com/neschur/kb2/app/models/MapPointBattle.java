package com.neschur.kb2.app.models;

/**
 * Created by siarhei on 27.01.15.
 */
public class MapPointBattle extends MapPoint {
    private boolean move = false;

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }
}
