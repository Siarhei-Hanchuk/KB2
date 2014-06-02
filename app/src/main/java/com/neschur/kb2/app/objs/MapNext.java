package com.neschur.kb2.app.objs;

import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.ObjID;

public class MapNext extends Obj {

	public MapNext(Country country, int x, int y) {
		super(country, x, y);
	}

	@Override
	public int getID() {
		return ObjID.o_map;
	}

	@Override
	public int action() {
//		Player.getInstance().upAvalCountry();
//		ScreenController.pushMenus(new Message() {
//
//			@Override
//			public String getMessage() {
//				Player.getInstance().upAvalCountry();
//				return "Вы нашлик карту в новую страну";
//			}
//
//			@Override
//			public MenusType getType() {
//				return MenusType.MESSAGE;
//			}
//		});
//		delete();
		return 0;
	}

}
