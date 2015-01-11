package com.neschur.kb2.app.entities;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public class Capitan extends Entity {

	public Capitan(Country country, int x, int y) {
		super(country, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getID() {
		return R.drawable.capitan;
	}

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
