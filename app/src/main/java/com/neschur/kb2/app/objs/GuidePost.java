package com.neschur.kb2.app.objs;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public class GuidePost extends Obj {

	public GuidePost(Country country, int x, int y) {
		super(country, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getID() {
		return R.drawable.guidepost;
	}

	public int action() {
//		ScreenController.pushMenus(new MessageGuidePost());
		return 0;
	}
}
