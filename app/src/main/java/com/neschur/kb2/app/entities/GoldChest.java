package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public class GoldChest extends Entity {
	private Integer gold=0;

	public GoldChest(Country country, int x, int y) {
		super(country, x, y);
		gold =(int)(33*Math.random()+22)*10;
	}

	@Override
	public int getID() {
		return R.drawable.goldchest;
	}

	public int action() {
//		Menu menu=new MenuGold();
//		menu.setAddition(gold);
//		ScreenController.pushMenus(menu);
//		delete();
//		//Screen.update();
		return 0;
	}
	
}
