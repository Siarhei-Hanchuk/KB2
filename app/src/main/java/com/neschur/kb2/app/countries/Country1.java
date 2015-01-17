package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.entities.HarmfulMap;

public class Country1 extends Country {

    protected int goldChestMax() {
        return 550;
    }

    protected int goldChestMin() {
        return 220;
    }

    public Country1(int id) {
        super(id);
        river(30);
        river(20);
        river(40);
        landscape(7, R.drawable.forest);
        landscape(20, R.drawable.stone);
        mapNext();
//        capitans();
//        castels();
        cities();
        goldChests(40);
        army();

        new HarmfulMap(this, 8, 5); //debug
    }

    void army() {
/*	    int v=0+51;
        int x;
	    int y;
	    while(v<10+51){
	        x=rand(65);
	        y=rand(65);
	            if((map[x][y].obj==null)&&(map[x][y].land==R.drawable.land)){
	                map[x][y].obj=R.drawable.army;
	                map[x][y].addid=v;
	                v++;
	            }
	    }*/
    }
}
