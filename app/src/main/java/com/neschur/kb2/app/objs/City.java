package com.neschur.kb2.app.objs;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public class City extends Obj {
	private int workers[]=new int[4];

	public City(Country country, int x, int y) {
		super(country, x, y);
		workers[0]=4;
		workers[1]=5;
		workers[2]=6;
		workers[3]=1;
	}

	@Override
	public int getID() {
		return R.drawable.city;
	}

	@Override
	public int action() {
//		Menu m=new MenuCity();
//		m.setAddition(this);
//		ScreenController.pushMenus(m);
		return 0;
	}	
	
	public int getWorkers(int i) {
		return workers[i-1];
	}
}
