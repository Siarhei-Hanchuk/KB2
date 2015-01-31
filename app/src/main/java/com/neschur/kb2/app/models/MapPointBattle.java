package com.neschur.kb2.app.models;

import com.neschur.kb2.app.entities.Entity;
import com.neschur.kb2.app.entities.WarriorEntity;

/**
 * Created by siarhei on 27.01.15.
 */
public class MapPointBattle extends MapPoint {
    private boolean move = false;
    private WarriorEntity entity;

    public MapPointBattle(Glade glade, int x, int y) {
        super(glade, x, y);
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    @Override
    public WarriorEntity getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Entity entity) {
        this.entity = (WarriorEntity)entity;
    }
}
