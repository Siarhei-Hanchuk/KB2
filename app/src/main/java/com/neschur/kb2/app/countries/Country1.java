package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

public class Country1 extends Country{
	public Country1()
	{
		super();
	    river(30);
	    river(20);
	    river(40);
	    landscape(7, R.drawable.forest);
	    landscape(20,R.drawable.stone);
	    mapnext();
	    //landscape();
	    //capitans();
	    //castels(gw_c1_capitan);
	    //cities();
	    goldchest(40);
	    army();
	    //временное:
	    //map[7][5].obj=R.drawable.capitan;
	    //map[7][5].addid=49;
	    //capitan(49);
	}

	void city(int cid){
	    /*city_arr[cid].castleid=cid;
	    city_arr[cid].magic=rand()%14;
	    city_arr[cid].wforest=rand()%4+1;
	    city_arr[cid].wland=rand()%4+1;
	    city_arr[cid].wstone=rand()%3+1;
	    city_arr[cid].wraft=rand()%4+1;*/
	}

	void capitan(int cid)
	{
	    /*capitan_arr[cid].army[0].armid=a_peasant;
	    capitan_arr[cid].army[0].count=10;
	    capitan_arr[cid].army[1].armid=a_elf;
	    capitan_arr[cid].army[1].count=5;
	    capitan_arr[cid].army[2].armid=0;
	    capitan_arr[cid].army[3].armid=0;
	    capitan_arr[cid].army[4].armid=0;*/
	}

	void army()
	{
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

	/*void Country1::landscape()
	{//Заполнения равнолесья лесом, скалами и тп.
	    gint16 r=0;
	    gint16 r2=0;
	    for(gint16 i=6;i<MAXC-6; i++){
	        for(gint16 j=6;j<MAXC-6; j++){
	            r=rand()%7;
	            r2=rand()%20;
	            if((map[i][j].obj==0)&&(map[i][j].land==l_land)){
	                if(r==1){map[i][j].land=l_forest;}
	                if(r2==1){map[i][j].land=l_stone;}
	            }
	        }
	    }
	}*/
}
