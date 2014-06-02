package com.neschur.kb2.app.objs;

import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.ObjID;

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
		return ObjID.o_city;
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
