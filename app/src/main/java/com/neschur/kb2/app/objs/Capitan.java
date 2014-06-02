package com.neschur.kb2.app.objs;

import com.neschur.kb2.app.countries.Country;
import com.neschur.kb2.app.models.ObjID;

public class Capitan extends Obj{

	public Capitan(Country country, int x, int y) {
		super(country, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getID() {
		return ObjID.o_capitan;
	}

	@Override
	public int action() {
//		ScreenController.pushMenus(new CapitanAsk());
		return 0;
	}
	//Костыль
	@Override
	public void ncAction() {
//		//System.out.println("NC");
//		final Player pl=Player.getInstance();
//		if(Math.abs(pl.getX()-x)+Math.abs(pl.getY()-y)<3){
//			ScreenController.setKeyHook(new KeyHook() {
//
//				@Override
//				public boolean exec(int key) {
//					step(pl.getX()-x,pl.getY()-y);
//					return false;
//				}
//			});
//			//System.out.println("go");
//
//		}
	}

}
