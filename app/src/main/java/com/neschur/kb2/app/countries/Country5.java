package com.neschur.kb2.app.countries;

import com.neschur.kb2.app.R;

public class Country5 extends Country{
	void sand_c5()
	{
	    for(int i=6;i<59;i++){
	        for(int j=6;j<11;j++){
	         map[i][j].land = R.drawable.sand;
	         map[j][i].land = R.drawable.sand;
	        }
	    }
	    for(int i=6;i<59;i++){
	        for(int j=54;j<59;j++){
	         map[j][i].land = R.drawable.sand;
	         map[i][j].land = R.drawable.sand;
	        }
	    }
	}

	/*void stone_c5()
	{
	    for(int i=11;i<MAXC-11;i++){
	        for(int j=11;j<MAXC-11;j++){
	            if((map[i][j].obj==0)&&(map[i][j].land==R.drawable.land)){
	                int r=rand(30);
	                if(r<16){
	                    map[i][j].land=R.drawable.stone;
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
