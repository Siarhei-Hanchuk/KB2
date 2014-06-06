package com.neschur.kb2.app.models;

import com.neschur.kb2.app.objs.Obj;

/**
 * Created by siarhei on 2.6.14.
 */
public class MapPoint {
    public int land;
    public Obj obj;
    public int addid;

    public MapPoint() {
        land=-1;
        obj=null;
    }

    public String toString(){
        return ("Земля: "+land+" Обект: "+obj.toString()+" Id Объекта: "+addid);
    }

    public int getLand() {
        return land;
    }

    public int getDrawable(){
        if(this.obj == null) {
            return land;
        }else{
            return obj.getID();
        }
    }

    public void setLand(int land) {
        this.land = land;
    }

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

    public int getAddid() {
        return addid;
    }

    public void setAddid(int addid) {
        this.addid = addid;
    }
}