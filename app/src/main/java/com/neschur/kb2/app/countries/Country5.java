package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.models.ObjID;

public class Country5 extends Country{
	void sand_c5()
	{
	    for(int i=6;i<59;i++){
	        for(int j=6;j<11;j++){
	         map[i][j].land= ObjID.l_sand;
	         map[j][i].land=ObjID.l_sand;
	        }
	    }
	    for(int i=6;i<59;i++){
	        for(int j=54;j<59;j++){
	         map[j][i].land=ObjID.l_sand;
	         map[i][j].land=ObjID.l_sand;
	        }
	    }
	}

	/*void stone_c5()
	{
	    for(int i=11;i<MAXC-11;i++){
	        for(int j=11;j<MAXC-11;j++){
	            if((map[i][j].obj==0)&&(map[i][j].land==ObjID.l_land)){
	                int r=rand(30);
	                if(r<16){
	                    map[i][j].land=ObjID.l_stone;
	                }
	            }
	        }
	    }
	}*/

	public Country5()
	{
	    sand_c5();
	    river(10);
	    //cities();
	    //stone_c5();
	    goldchest(100);
	}
}
