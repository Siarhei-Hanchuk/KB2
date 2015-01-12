package com.neschur.kb2.app.models;

import com.neschur.kb2.app.entities.Entity;

/**
 * Created by siarhei on 2.6.14.
 */
public class MapPoint {
    public int land;
    private Entity entity;
//    public int addid;

    public MapPoint() {
        land=-1;
        entity =null;
    }

    public String toString(){
//        return ("Земля: "+land+" Обект: "+ entity.toString()+" Id Объекта: "+addid);
        return ("Земля: "+land+" Обект: "+ entity.toString());
    }

    public int getLand() {
        return land;
    }

    public int getDrawable(){
        if(this.entity == null) {
            return land;
        }else{
            return entity.getID();
        }
    }

    public void setLand(int land) {
        this.land = land;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

//    public int getAddid() {
//        return addid;
//    }
//
//    public void setAddid(int addid) {
//        this.addid = addid;
//    }
}