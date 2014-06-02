package com.neschur.kb2.app.objs;

import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.ObjID;

public class Magican extends Obj{

	public Magican(Country country, int x, int y) {
		super(country, x, y);
	}

	@Override
	public int getID() {
		return ObjID.MAGICAN;
	}

	@Override
	public int action() {
//		ScreenController.pushMenus(new MagicanMenu());
//		delete();
		return 0;
	}
}
