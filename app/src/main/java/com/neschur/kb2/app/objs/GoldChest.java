package com.neschur.kb2.app.objs;

import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.ObjID;

public class GoldChest extends Obj{
	private Integer gold=0;

	public GoldChest(Country country, int x, int y) {
		super(country, x, y);
		gold =(int)(33*Math.random()+22)*10;
	}

	@Override
	public int getID() {
		return ObjID.o_goldchest;
	}

	@Override
	public int action() {
//		Menu menu=new MenuGold();
//		menu.setAddition(gold);
//		ScreenController.pushMenus(menu);
//		delete();
//		//Screen.update();
		return 0;
	}
	
}
