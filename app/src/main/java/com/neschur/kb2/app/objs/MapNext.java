package com.neschur.kb2.app.objs;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public class MapNext extends Obj {

	public MapNext(Country country, int x, int y) {
		super(country, x, y);
	}

	@Override
	public int getID() {
		return R.drawable.map;
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
