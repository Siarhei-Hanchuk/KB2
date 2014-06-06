package com.neschur.kb2.app.objs;

import com.neschur.kb2.app.R;
import com.neschur.kb2.app.countries.Country;

public class Nave extends Obj{

	public Nave(Country country, int x, int y) {
		super(country, x, y);
	}

	@Override
	public int getID() {
		return R.drawable.nave;
	}

	@Override
	public int action() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void move(int x,int y){
		this.x=x;
		this.y=y;
	}
}
